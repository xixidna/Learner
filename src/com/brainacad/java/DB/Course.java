package com.brainacad.java.DB;
import java.sql.*;

public class Course {

    // Add a new course into table "Course"
    public void addNewCourse(Connection connection, String name, String description, String dateStart, String dateFinish, int days) {
        try {
            connection.setAutoCommit(false);
            String query = "INSERT INTO Course ('name', 'description', 'dateStart', 'dateFinish', 'days') "
                    + "VALUES (?, ?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, description);
                preparedStatement.setString(3, dateStart);
                preparedStatement.setString(4, dateFinish);
                preparedStatement.setInt(5, days);
                preparedStatement.executeUpdate();
                System.out.println("Preparing is OK");
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
                System.out.println("Insert is FALSE, Rollback");
            } finally {
                connection.commit();
                System.out.println("A new course is added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all courses from table "Course"
    public void getAllCourses(Connection connection) {
        try {
            connection.setAutoCommit(false);
            String query = "SELECT * from Course";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                outData(preparedStatement.executeQuery());
                System.out.println("Preparing is OK");
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
                System.out.println("Select of courses is FALSE, Rollback");
            } finally {
                connection.commit();
                System.out.println("Select of courses is OK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get a course from table "Course"
    public void getCourse(Connection connection, int idCourse) {
        try {
            connection.setAutoCommit(false);
            String query = "SELECT DISTINCT Course.idCourse, Course.name, Course.description, Course.dateStart, Course.dateFinish, Course.days, " +
                    "Coach.firstName, Coach.lastName FROM Coach " +
                    "inner join CoachCourse on Coach.idCoach = CoachCourse.idCoach " +
                    "inner join Course on CoachCourse.idCourse = Course.idCourse " +
                    "WHERE Course.idCourse = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idCourse);
                outData(preparedStatement.executeQuery());
                System.out.println("Preparing is OK");
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
                System.out.println("Select a course is FALSE, Rollback");
            } finally {
                connection.commit();
                System.out.println("Get a course is OK");
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
