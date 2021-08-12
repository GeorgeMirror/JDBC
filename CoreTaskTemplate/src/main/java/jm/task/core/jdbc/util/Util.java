package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/schea1";
    private String userName = "root";
    private String pass = "as3a9wcx";

    public void setConnection(){
        try {
            connection = DriverManager.getConnection(url, userName, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public Connection getConenction() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
