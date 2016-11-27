package com.brainacad.java.learner;

import com.brainacad.java.learner.DB.Coach;
import com.brainacad.java.learner.DB.Course;
import com.brainacad.java.learner.DB.DBConnect;
import com.brainacad.java.learner.DB.Student;
import com.brainacad.java.learner.UI.MenuClass;

import java.sql.Connection;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        DBConnect dbconnect = new DBConnect();
        Connection connection = dbconnect.connect();

//        Statement statement = dbconnect.getStatementDB();

//        course.addNewCourse(connection, "Java Advanced 2", "New course", "2017.01.10", "2017.02.10", 15);
//        course.getAllCourses(connection);
//        course.getCourse(connection, 5);

//        Coach coach = new Coach();
//        coach.addNewCoach(connection, "Derek", "Zoolander", 5);
//        coach.getCoach(connection, 33);
//        coach.getAllCoaches(connection);

//        Student student = new Student();
//        student.getStudent(connection, 3);
//        student.addNewStudent(connection, "Roman", "Zendul", 35, "1, 2, 3");
//        student.getAllStudents(connection);

        MenuClass.Menu();
        MenuClass.Input(connection);

//        dbconnect.closeStatemntDB();
        dbconnect.disconnect();

    }
}