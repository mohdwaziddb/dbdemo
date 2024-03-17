package com.main.dbdemo.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDatabase {
    public static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/dbdemo";
        String user = "root";
        String password = "root";

        return DriverManager.getConnection(url, user, password);
    }
}

