package library_system;

import java.sql.Connection;
import library_system.database.DBConnection;

public class TestConnection {

    public static void main(String[] args) {
        try {
            Connection connection = DBConnection.getConnection();
            System.out.println("Database connected successfully!");
            connection.close();
        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}