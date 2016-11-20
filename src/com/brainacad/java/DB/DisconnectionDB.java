package com.brainacad.java.DB;

import java.sql.SQLException;

public class DisconnectionDB {

    public DisconnectionDB() {

            try {
                if (ConnectionDB.connection != null) {
                    ConnectionDB.connection.close();
                    System.out.println("Connection closed");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }