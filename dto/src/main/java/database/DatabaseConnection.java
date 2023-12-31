package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    //Load properties from db.properties to connect with database
    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("/home/robert/Projects/databases-training-projects/project_DataAccessObject/db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DBException(e.getMessage());
        }
    }

    //Establish connection with database
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("url");
                String user = props.getProperty("user");
                String password = props.getProperty("password");
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeResultSet() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static void closeStatement() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
