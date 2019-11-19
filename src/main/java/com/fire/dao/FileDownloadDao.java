package com.fire.dao;

import com.fire.Util.DerbyUtil;

import java.sql.*;

public class FileDownloadDao {
    
    public String getFilePathByUUID(String uuid) {
        String filePath = null;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DerbyUtil.getConn();
            statement = conn.prepareStatement("select filePath from file_data where fileName = ?");
            ResultSet resultSet = statement.executeQuery();
            statement.setString(1,uuid);
            while (resultSet.next()){
                filePath = resultSet.getString("filePath");
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        } finally {
//            关闭连接
            if (statement != null){
                DerbyUtil.close(statement,conn);
            }
        }
        return filePath;
    }
}
