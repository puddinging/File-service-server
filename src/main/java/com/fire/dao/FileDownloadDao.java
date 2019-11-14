package com.fire.dao;

import com.fire.Util.DerbyUtil;

import java.sql.*;

public class FileDownloadDao {
    
//    根据uuid获取文件
// TODO: 2019/11/13  
    public String getFilePathByUUID(String uuid) {
        String filePath = null;
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DerbyUtil.getConn();
            statement = conn.createStatement();
            // TODO: 2019/11/13 sql语句动态参数
            ResultSet resultSet = statement.executeQuery("select filePath from file_data where fileName = ");
            while (resultSet.next()){
                filePath = null;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        } finally {
//            关闭连接
            DerbyUtil.close(statement,conn);
        }
        return filePath;
    }
}
