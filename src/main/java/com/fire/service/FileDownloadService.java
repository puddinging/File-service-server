package com.fire.service;

import com.fire.dao.FileDownloadDao;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

public class FileDownloadService {
    public void fileDownload(HttpServletRequest req, HttpServletResponse resp) {
        try {
            //        获取uuid
            String uuid = req.getParameter("uuid");
//        获取文件路径
            String filePath = new FileDownloadDao().getFilePathByUUID(uuid);
//        将文件写入输出流
            FileInputStream fis = new FileInputStream(filePath);
            resp.setCharacterEncoding("utf-8");
            resp.setHeader("Content-Disposition", "attachment; filename="+filePath);
            ServletOutputStream out = resp.getOutputStream();
            byte[] bt = new byte[1024];
            int length = 0;
            while((length=fis.read(bt))!=-1){
                out.write(bt,0,length);
            }
            out.close();
        }catch (IOException e){
            resp.setStatus(401);
        }
    }
}
