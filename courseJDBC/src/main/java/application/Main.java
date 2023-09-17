package application;

import database.DatabaseConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        DatabaseConnection.closeConnection();
    }
}