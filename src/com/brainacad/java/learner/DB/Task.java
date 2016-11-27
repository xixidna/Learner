package com.brainacad.java.learner.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Task {

    // Add a new task into table "Task"
    public void addNewTask(Connection connection, String name, int idCourse) {
        try {
            connection.setAutoCommit(false);
            String query = "INSERT INTO Task ('name', 'idCourse') "
                    + "VALUES (?, ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, idCourse);
                preparedStatement.executeUpdate();
                System.out.println("Preparing is OK");
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
                System.out.println("Insert is FALSE, Rollback");
            } finally {
                connection.commit();
                System.out.println("A new task is added");
            }
            // Show added student
            query = "SELECT DISTINCT Task.idTask, Task.name AS Task, Course.name AS Course FROM TASK " +
                    "INNER JOIN Course ON Course.idCourse = Task.idCourse WHERE Task.name = ? AND Task.idCourse = ?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, idCourse);
                Output.outData(preparedStatement.executeQuery());
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Select of added task is FALSE, Rollback");
            } finally {
                System.out.println("Select of added task is OK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all tasks from table "Task"
    public void getAllTasks(Connection connection) {
        String query = "SELECT Task.idTask, Task.name AS Task, Course.name AS Course from Task " +
                "INNER JOIN Course ON Course.idCourse = Task.idCourse ORDER BY Course.name";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            Output.outData(preparedStatement.executeQuery());
            System.out.println("Preparing is OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Select of tasks is FALSE, Rollback");
        } finally {
            System.out.println("Select of tasks is OK");
        }
    }
}