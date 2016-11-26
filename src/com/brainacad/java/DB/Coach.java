package com.brainacad.java.DB;

import java.sql.*;

public class Coach {

    // Add a new coach into table "Coach"
    public void addNewCoach(Connection connection, String firstName, String lastName, int idCourse) {
        try {
            connection.setAutoCommit(false);
            String query = "INSERT INTO Coach ('firstName', 'lastName') "
                    + "VALUES (?, ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.executeUpdate();
                System.out.println("Preparing a new coach is OK");
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
                System.out.println("Insert of coach is FALSE, Rollback");
            } finally {
                connection.commit();
                System.out.println("A new coach is added");
            }
            query = "INSERT INTO CoachCourse ('idCoach', 'idCourse') " +
                    "VALUES ((SELECT DISTINCT idCoach FROM Coach WHERE firstName = ? AND lastName = ?), ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setInt(3, idCourse);
                preparedStatement.execute();
                System.out.println("Preparing a new coach on course is OK");
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
                System.out.println("Insert of a new coach on a course is FALSE, Rollback");
            } finally {
                connection.commit();
                System.out.println("A course for a new coach is assigned");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all coaches from table "Coach"
    public void getAllCoaches(Connection connection) {
        try {
            connection.setAutoCommit(false);
            String query = "SELECT DISTINCT firstName, lastName from Coach";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                outData(preparedStatement.executeQuery());
                System.out.println("Preparing is OK");
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
                System.out.println("Select is FALSE, Rollback");
            } finally {
                connection.commit();
                System.out.println("Select coaches is OK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get a coach from table "Coach"
    public void getCoach(Connection connection, int idCoach) {
        try {
            connection.setAutoCommit(false);
            String query = "SELECT DISTINCT Coach.firstName, Coach.lastName, Course.name, Course.description FROM Coach " +
                    "inner join CoachCourse on Coach.idCoach = CoachCourse.idCoach " +
                    "inner join Course on CoachCourse.idCourse = Course.idCourse " +
                    "WHERE Coach.idCoach = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idCoach);
                outData(preparedStatement.executeQuery());
                System.out.println("Preparing is OK");
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
                System.out.println("Select a Coach is FALSE, Rollback");
            } finally {
                connection.commit();
                System.out.println("Get a coach is OK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Output data from ResultSet
    private void outData(ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            System.out.print(resultSetMetaData.getColumnName(i) + "\t\t\t");
        }
        System.out.println();

        while (resultSet.next()) {
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                System.out.print(resultSet.getString(i) + "\t\t\t");
            }
            System.out.println();
        }
    }
}
