package com.brainacad.java;

import com.brainacad.java.DB.Coach;
import com.brainacad.java.DB.Course;
import com.brainacad.java.DB.DBConnect;
import com.brainacad.java.DB.Student;

import java.sql.Connection;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        DBConnect dbconnect = new DBConnect();
        Connection connection = dbconnect.connect();

//        Statement statement = dbconnect.getStatementDB();

        Course course = new Course();
//        course.addNewCourse(connection, "Java Advanced 2", "New course", "2017.01.10", "2017.02.10", 15);
//        course.getAllCourses(connection);
//        course.getCourse(connection, 5);

        Coach coach = new Coach();
//        coach.addNewCoach(connection, "Derek", "Zoolander", 5);
//        coach.getCoach(connection, 33);
//        coach.getAllCoaches(connection);

        Student student = new Student();
        student.getStudent(connection, 3);
        student.addNewStudent(connection, "Roman", "Zendul", 35, "1, 2, 3");
        student.getAllStudents(connection);

        dbconnect.closeStatemntDB();
        dbconnect.disconnect();

    }
}