package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static String url;
    private static String user;
    private static String password;

    public static Connection connect() {
        url = "jdbc:mysql://localhost:3306/praktikum";
        user = "root";
        password = "";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established successfully.");
        } catch (Exception e) {
            System.err.println("Connection failed: " + e.getMessage());
        }

        return connection;
    }
}