package com.fire.Util;

import java.sql.*;
import java.util.Properties;

public class DerbyUtil {
    public static Connection getConn() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        驱动
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            System.out.println("Loaded the EmbeddedDriver");
            Connection conn = null;
            Properties props = new Properties();
//            配置
            props.setProperty("user", "user");
            props.setProperty("password", "password");
            try {
//                如果没有会创建，传入配置信息
                conn = DriverManager.getConnection("jdbc:derby:helloDB;create=true", props);
                System.out.println("create derbyDB");
//                关闭自动commit
                conn.setAutoCommit(false);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return conn;
    }

    public static void close(Statement statement, Connection conn) {
//        每次使用之后必须关闭，在此手动提交事务
        try {
            statement.close();
            conn.commit();
            conn.close();
            DriverManager.getConnection("jdbc:derby:helloDB;shutDown=true");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
