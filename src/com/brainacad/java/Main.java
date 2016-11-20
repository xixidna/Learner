package com.brainacad.java;

import com.brainacad.java.DB.ConnectionDB;
import com.brainacad.java.DB.DisconnectionDB;
import com.brainacad.java.DB.StatementDB;

public class Main {

    public static void main(String[] args) {

        ConnectionDB connectionDB = new ConnectionDB();
        StatementDB statementDB = new StatementDB();
        statementDB.getAllCourses();

        DisconnectionDB disconnectionDB = new DisconnectionDB();




    }
}