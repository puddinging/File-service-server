package com.fire.service;

import com.fire.Pojo.FileData;
import com.fire.Util.AESUtil;
import com.fire.Util.RsaUtil;
import com.fire.dao.FileUploadDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FileUploadService {

    public  void fileUpload(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {


//            获取输入文件流
            Part part = req.getPart("file");
            InputStream is = part.getInputStream();
//            获取文件大小
            Integer fileSize = null;

//            文件名
            String fileName = part.getHeader("Content-Disposition");

//            获取文件后缀名
            String fileType = null;

//            存储文件夹路径
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String date = sdf.format(new Date().getTime());
            String filePath = "E:"+File.separator+date;

            File dest = new File(filePath);
            dest.mkdirs();

//            将文件加密存储在指定的文件夹里面,返回文件名字和加密的密钥
            Map<String,String> dataMap = EncryptedStorage(is,filePath);

            if (dataMap.isEmpty() || dataMap == null){
//                返回加密失败
                writer.write("{\"message\": "+"加密失败，请重试"+"}");
                return;
            }

//            使用公钥将aesKey加密成数字信封
            String digitalEnvelope = RsaUtil.encryptionByPub(dataMap.get("aesKey"));

//            持久化文件元数据
            FileData fileData = new FileData(fileSize,fileType,fileName,new Date(),filePath,digitalEnvelope);
            new FileUploadDao().keepFileData(fileData);
//            返回uuid

            String respData = "{\"UUID\": "+dataMap.get("fileName")+"}";
            if (writer != null) {
                writer.write(respData);
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过文件输入流和文件路径，将文件加密到文件路径下，返回文件名字和aesKey
     * @param is
     * @param filePath
     * @return
     */
    private Map<String, String> EncryptedStorage(InputStream is, String filePath) {
        String aesKey = UUID.randomUUID().toString();
        String fileName = UUID.randomUUID().toString();

        // TODO: 2019/11/13 具体的加密逻辑 如果加密失败返回一个空值
        if(!AESUtil.encryptfile(is,filePath,aesKey,fileName)){
            return null;
        }

        Map<String,String> map = new HashMap<>();
        map.put("aesKey",aesKey);
        map.put("fileName",fileName);
        return map;
    }
}
