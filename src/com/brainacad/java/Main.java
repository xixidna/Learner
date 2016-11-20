package com.brainacad.java;

import com.brainacad.java.DB.ConnectionDB;
import com.brainacad.java.DB.DisconnectionDB;
import com.brainacad.java.DB.StatementDB;

public class Main {

    public static void main(String[] args) {

        new ConnectionDB();
        StatementDB statement = new StatementDB();
        statement.AddNewCourse("Java Advanced 2", "2017.01.10", "2017.02.10", 15);
        statement.getAllCourses();
        new DisconnectionDB();


    }
}