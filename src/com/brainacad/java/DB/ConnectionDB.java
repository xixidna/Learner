package com.brainacad.java.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    static Connection connection = null;

    public ConnectionDB() {

            try {
                connection = DriverManager.getConnection("jdbc:sqlite:LearnerDB.db");
                System.out.println("Connection established");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("...Start working with DB...");

//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                    System.out.println("Connection closed");
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
