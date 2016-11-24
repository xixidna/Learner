package com.brainacad.java.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Coach {

    // Add a new coach into table "Coach"
    public void addNewCoach(Connection connection, String firstName, String lastName) {
        try {
            connection.setAutoCommit(false);
            String query = "INSERT INTO Coach ('firstName', 'lastName') "
                    + "VALUES (?, ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.executeUpdate();
                System.out.println("Preparing OK");
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
                System.out.println("Insert FALSE, Rollback");
            } finally {
                connection.commit();
                System.out.println("New coach is added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
