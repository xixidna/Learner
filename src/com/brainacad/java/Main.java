package com.brainacad.java;

import com.brainacad.java.DB.Course;
import com.brainacad.java.DB.DBConnect;

import java.sql.Connection;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        DBConnect dbconnect = new DBConnect();
        Connection connection = dbconnect.connect();

        Statement statement = dbconnect.getStatementDB();

        Course course = new Course();
        course.addNewCourse(connection, "Java Advanced 2", "2017.01.10", "2017.02.10", 15);
        course.getAllCourses(statement, connection);

        dbconnect.closeStatemntDB();
        dbconnect.disconnect();

    }
}