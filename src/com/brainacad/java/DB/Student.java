package com.brainacad.java.DB;

import java.sql.*;

public class Student {

    // Add a new student into table "Student"
    public void addNewStudent(Connection connection, String firstName, String lastName, int age, String courses) {
        try {
            connection.setAutoCommit(false);
            String query = "INSERT INTO Student ('firstName', 'lastName', 'age') "
                    + "VALUES (?, ?, ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setInt(3, age);
                preparedStatement.executeUpdate();
                System.out.println("Preparing is OK");
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
                System.out.println("Insert is FALSE, Rollback");
            } finally {
                connection.commit();
                System.out.println("A new student is added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all students from table "Student"
    public void getAllStudents(Connection connection) {
        try {
            connection.setAutoCommit(false);
            String query = "SELECT * from Student";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                outData(preparedStatement.executeQuery());
                System.out.println("Preparing is OK");
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
                System.out.println("Select of students is FALSE, Rollback");
            } finally {
                connection.commit();
                System.out.println("Select of students is OK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get a student from table "Student"
    public void getStudent(Connection connection, int idStudent) {
        try {
            connection.setAutoCommit(false);
            String query = "SELECT DISTINCT Student.idStudent, Student.firstName, Student.lastName, Student.age, Course.name FROM Student " +
                    "inner join StudentCourse on Student.idStudent = StudentCourse.idStudent " +
                    "inner join Course on StudentCourse.idCourse = Course.idCourse " +
                    "WHERE Student.idStudent = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idStudent);
                outData(preparedStatement.executeQuery());
                System.out.println("Preparing is OK");
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
                System.out.println("Select a student is FALSE, Rollback");
            } finally {
                connection.commit();
                System.out.println("Get a student is OK");
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
