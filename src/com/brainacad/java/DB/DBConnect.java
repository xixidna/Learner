package com.brainacad.java.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

    private Connection connection = null;
    private Statement statement = null;

    // Connecting to DB
    public Connection connect() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:LearnerDB.db");
            System.out.println("Connection established");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("...Start working with DB...");
        return connection;
    }

    // Closing connection
    public boolean disconnect() {

        try {
            if (connection != null) {
                connection.close();
                System.out.println("...Connection closed...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // Create statement
    public Statement getStatementDB() {
        try {
            statement = connection.createStatement();
            System.out.println("Statement created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    // Close statement
    public boolean closeStatemntDB() {
        try {
            if (statement != null) {
                statement.close();
                System.out.println("Statement closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
