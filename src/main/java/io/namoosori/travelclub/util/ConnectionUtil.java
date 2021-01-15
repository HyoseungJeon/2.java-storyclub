package io.namoosori.travelclub.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/storyclub", "plateer", "1234");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
