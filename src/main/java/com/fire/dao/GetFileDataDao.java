package com.fire.dao;

import com.fire.Pojo.FileData;
import com.fire.Util.DerbyUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetFileDataDao {
    public FileData getFileDataByUUID(String uuid) {
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DerbyUtil.getConn();
            statement = conn.createStatement();
            // TODO: 2019/11/13 sql语句动态参数
            ResultSet resultSet = statement.executeQuery("select * from file_data where fileName = ");
            while (resultSet.next()){
                FileData fileData = new FileData();
                // TODO: 2019/11/13 获取参数，填充对象
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        } finally {
//            关闭连接
            DerbyUtil.close(statement,conn);
        }
        return null;
    }
}
