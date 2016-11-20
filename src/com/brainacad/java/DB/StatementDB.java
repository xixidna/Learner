package com.brainacad.java.DB;

import java.sql.*;

public class StatementDB {

    private static Statement statement = null;

    // Create and close statement
    public StatementDB() {

        try {
            statement = ConnectionDB.connection.createStatement();
            System.out.println("Statement created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (statement != null) {
                statement.close();
                System.out.println("Statement closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // TEST Method: Get all data from table "Courses"
    public void getAllCourses() {

        try {
            statement = ConnectionDB.connection.createStatement();
            System.out.println("Statement created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            ResultSet resultSet = statement.executeQuery("SELECT * from Course");

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

//            while (resultSet.next()){
//                System.out.println(
//                            resultSet.getInt(1) + ": " +
//                            resultSet.getString("name") + ", " +
//                            resultSet.getString("dateStart") + ", " +
//                            resultSet.getString("dateFinish") + ", " +
//                            resultSet.getInt(5));
//            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (statement != null) {
                statement.close();
                System.out.println("Statement closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TEST Method: Add new Course into table "Courses"
    public void AddNewCourse(String name, String dateStart, String dateFinish, int days) {

        try {
            statement = ConnectionDB.connection.createStatement();
            System.out.println("Statement created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ConnectionDB.connection.setAutoCommit(false);
            String query = "INSERT INTO Course ('name', 'dateStart', 'dateFinish', 'days') "
                    + "VALUES (?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = ConnectionDB.connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, dateStart);
                preparedStatement.setString(3, dateFinish);
                preparedStatement.setInt(4, days);
                preparedStatement.executeUpdate();
                System.out.println("Preparing OK");
            } catch (SQLException e) {
                e.printStackTrace();
                ConnectionDB.connection.rollback();
                System.out.println("Insert FALSE, Rollback");
            } finally {
                ConnectionDB.connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//          statement.execute("INSERT INTO Course (name, dateStart, dateFinish, days) VALUES ('Java SE Professional', '2017.05.01', '2017.07.01', 21)");
//          System.out.println("Data inserted");

        try {
            if (statement != null) {
                statement.close();
                System.out.println("Statement closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}