package com.fire.Util;

import java.sql.*;
import java.util.Properties;

public class DerbyUtil {
    public static Connection getConn() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            System.out.println("Loaded the EmbeddedDriver");
            Connection conn = null;
            Properties props = new Properties();
            props.setProperty("user", "user");
            props.setProperty("password", "password");
            try {
                conn = DriverManager.getConnection("jdbc:derby:helloDB;create=true", props);
                System.out.println("create derbyDB");
                conn.setAutoCommit(false);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return conn;
    }

    public static void close(Statement statement, Connection conn) {
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
