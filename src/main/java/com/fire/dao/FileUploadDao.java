package com.fire.dao;

import com.fire.Pojo.FileData;
import com.fire.Util.DerbyUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class FileUploadDao {
//    持久化元数据
    public void keepFileData(FileData fileData) {
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DerbyUtil.getConn();
            statement = conn.createStatement();
            // TODO: 2019/11/13 sql语句参数
            statement.executeQuery("insert into fileData values ()");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        } finally {
//            关闭连接
            DerbyUtil.close(statement,conn);
        }
    }
}
