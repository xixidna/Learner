package com.brainacad.java.learner;

import com.brainacad.java.learner.DB.DBConnect;
import com.brainacad.java.learner.UI.MenuClass;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        DBConnect dbconnect = new DBConnect();
        Connection connection = dbconnect.connect();

        MenuClass.Menu();
        MenuClass.Input(connection);

        dbconnect.disconnect();

    }
}