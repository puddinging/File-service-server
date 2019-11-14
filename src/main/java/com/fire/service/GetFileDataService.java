package com.fire.service;

import com.fire.Pojo.FileData;
import com.fire.dao.GetFileDataDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class GetFileDataService {
    public void getFileData(HttpServletRequest req, HttpServletResponse resp) {
        String uuid = req.getParameter("UUID");
        FileData fileData = new GetFileDataDao().getFileDataByUUID(uuid);
//        返回
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        try {
            PrintWriter writer = resp.getWriter();
            writer.write(fileData.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
