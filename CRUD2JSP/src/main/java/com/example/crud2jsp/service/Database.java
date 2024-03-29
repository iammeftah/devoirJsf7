package com.example.crud2jsp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://mysql-14ec272b-meftahahmedreda02-60b9.a.aivencloud.com:19015/defaultdb" ;
    private static final String USERNAME = "avnadmin";
    private static final String PASSWORD = "AVNS_pSy0TkV7Fd2-R1s944x";

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            throw new IllegalStateException("Failed to connect to database");
        }
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL , USERNAME , PASSWORD);
    }

    public static void closeConnection(Connection connection){
        if(connection != null ){
            try{
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
