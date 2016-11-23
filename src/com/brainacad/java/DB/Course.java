package com.brainacad.java.DB;
import java.sql.*;

public class Course {

    // TEST Method: Add new Course into table "Courses"
    public void addNewCourse(Connection connection, String name, String dateStart, String dateFinish, int days) {
        try {
            connection.setAutoCommit(false);
            String query = "INSERT INTO Course ('name', 'dateStart', 'dateFinish', 'days') "
                    + "VALUES (?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, dateStart);
                preparedStatement.setString(3, dateFinish);
                preparedStatement.setInt(4, days);
                preparedStatement.executeUpdate();
                System.out.println("Preparing OK");
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
                System.out.println("Insert FALSE, Rollback");
            } finally {
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TEST Method: Get all data from table "Courses"
    public void getAllCourses(Statement statement, Connection connection) {
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
            resultSet.close();

    // TEST: output Courses
//            String query = "SELECT * from Course;";
//            resultSetMetaData = resultSet.getMetaData();
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                resultSet = preparedStatement.executeQuery();
//                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
//                    System.out.print(resultSetMetaData.getColumnName(i) + "\t\t\t");
//                }
//                System.out.println();
//
//                while (resultSet.next()) {
//                    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
//                        System.out.print(resultSet.getString(i) + "\t\t\t");
//                    }
//                    System.out.println();
//                }
//            }
//
//            while (resultSet.next()){
//                System.out.println(
//                            resultSet.getInt(1) + ": " +
//                            resultSet.getString("name") + ", " +
//                            resultSet.getString("dateStart") + ", " +
//                            resultSet.getString("dateFinish") + ", " +
//                            resultSet.getInt(5));
//            }
//
//            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
