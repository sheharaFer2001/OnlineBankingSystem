/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlinebankingsystem;
import java.sql.*;
/**
 *
 * @author Shehara PC
 */
public class DatabaseCon {
    private static final String SERVER_URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "abc_bank";
    private static final String FULL_URL = SERVER_URL + DATABASE_NAME;
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Try connecting directly
            connection = DriverManager.getConnection(FULL_URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");

        } catch (SQLException e) {
            System.out.println("Database not found. Trying to create it...");
            createDatabase(); // Try creating database
            try {
                // After creating, try connecting again
                connection = DriverManager.getConnection(FULL_URL, USER, PASSWORD);
                System.out.println("Database created and connected successfully!");
            } catch (SQLException ex) {
                System.out.println("Failed to connect even after creating database.");
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
        return connection;
    }

    private static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
            stmt.executeUpdate(sql);
            System.out.println("Database '" + DATABASE_NAME + "' created successfully!");

        } catch (SQLException e) {
            System.out.println("Error while creating database.");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Connection testConnection = getConnection();
        if (testConnection != null) {
            System.out.println("Test connection successful!");
            try {
                testConnection.close(); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Test connection failed!");
        }
    }
}
