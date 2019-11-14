package com.fire.controller;

import java.sql.*;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        try {
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

                Statement statement = conn.createStatement();
//                statement.execute("drop table user_uer");
                System.out.println("create table user_uer");
//                statement.execute("create table user_uer ( name varchar(20), score int)");
                statement.execute("insert into user_uer ( name,score) values ('111',89)");
                statement.execute("insert into user_uer (name ,score) values ('222',90)");
                ResultSet rSet = statement.executeQuery("select name,score from user_uer");
                System.out.println("------------------------");
                while (rSet.next()) {
                    System.out.println(rSet.getString("name"));
                    System.out.println(rSet.getInt("score"));
                }
                System.out.println("query user_uer data");
                rSet.close();
                statement.close();
                conn.commit();
                conn.close();
                DriverManager.getConnection("jdbc:derby:helloDB;shutDown=true");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
