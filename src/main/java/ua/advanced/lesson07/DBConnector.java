package ua.advanced.lesson07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBConnector {
    private Connection connection;
    private Statement statement;

    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;

    public DBConnector() {
        ResourceBundle resource = ResourceBundle.getBundle("advanced/database");
        URL = resource.getString("db.url");
        USERNAME = resource.getString("db.user");
        PASSWORD = resource.getString("db.password");
    }

    public void establishConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection is established!");
            try {
                statement = connection.createStatement();
                System.out.println("Statement is created!");
            } catch (SQLException e) {
                System.err.println("STATEMENT CREATION ERROR!");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.err.println("DATABASE CONNECTION ERROR!");
            e.printStackTrace();
        }
    }
    public void closeConnection(Connection connection) {
        try {
            if (statement != null) {
                statement.close();
                System.out.println("Statement is closed!");
                try {
                    connection.close();
                    System.out.println("Connection is closed!");
                } catch (SQLException e) {
                    System.err.println("CONNECTION CLOSE ERROR!");
                    e.printStackTrace();
                }
            } else {
                System.err.println("STATEMENT WASN'T CREATED!");
            }
        } catch (SQLException e) {
            System.err.println("STATEMENT CLOSE ERROR!");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
    public Statement getStatement() {
        return statement;
    }
}
