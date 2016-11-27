package com.brainacad.java.learner.UI;

import com.brainacad.java.learner.DB.Coach;
import com.brainacad.java.learner.DB.Course;
import com.brainacad.java.learner.DB.Student;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuClass {

    // Show menu cases
    public static void Menu() {
        System.out.println("Welcome to the learner system!\n" +
                "==============================\n" +
                "Create a new course: enter \"create course\" or [C]\n" +
                "Show a course: enter \"show course <cID>\" or [S]\n" +
                "Show all courses: enter \"show courses\" or [A]\n" +
                "Show all students on course: enter \"show students <cID>\" or [E]\n" +
                "Show a course journal: enter \"show journal <cID>\" or [O]\n" +
                "Save a course journal to a file: enter \"save journal <cID>\" or [P]\n" +
                "---\n" +
                "Create a new student: enter \"create student\" or [B]\n" +
                "Add a student to the course: enter \"transfer student <sID> add <cID>\" or [D]\n" +
                "Remove a student from the course: enter \"transfer student <sID> remove <cID>\" or [R]\n" +
                "---\n" +
                "Show all students: enter \"show students\" or [G]\n" +
                "Create a new course: enter \"create course\" or [C]\n" +
                "---\n" +
                "Create a new trainer: enter \"create trainer\" or [Y]\n" +
                "Show a trainer: enter \"show trainer <tID>\" or [U]\n" +
                "---\n" +
                "Create a new task: enter \"create task\" or [I]\n" +
                "---\n" +
                "Exit program: enter \"exit\" or [Q]\n" +
                "==============================\n");
    }

    // User input
    public static void Input(Connection connection) {

        Coach coach = null;
        Course course = null;
        Student student = null;
        String input;
        Pattern patternInput = Pattern.compile("\\w*\\s*\\w*\\s*(\\d*)\\s*\\w*\\s*(\\d*)");
        Pattern patternCourses = Pattern.compile("(\\d*)\\s*(\\d*)\\s*(\\d*)");
        Matcher matcherInput, matcherCourses;
        String id1 = null;
        String id2 = null;
        String firstName, lasttName, courses;
        String[] coursess = new String[10];
        int age;

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Make your choice: ");
            input = scanner.nextLine();

            matcherInput = patternInput.matcher(input);

            if (matcherInput.matches()) {
                if (!matcherInput.group(1).equals("")) {
                    id1 = matcherInput.group(1);
                    if (!matcherInput.group(2).equals("")) {
                        id2 = matcherInput.group(2);
                    }
                }
            }

            input = input.toUpperCase();
            System.out.println("Your choice is: " + input);

            if (input.contains("SHOW COURSE")) {
                input = "SHOW COURSE";
            }
            if (input.contains("SHOW STUDENTS")) {
                input = "SHOW STUDENTS";
            }
            if (input.contains("SHOW JOURNAL")) {
                input = "SHOW JOURNAL";
            }
            if (input.contains("SAVE JOURNAL")) {
                input = "SAVE JOURNAL";
            }
            if (input.contains("SHOW TRAINER")) {
                input = "SHOW TRAINER";
            }
            if (input.contains("TRANSFER STUDENT")) {
                if (input.contains("REMOVE")) {
                    input = "REMOVE STUDENT";
                }
                else input = "MOVE STUDENT";
            }

            switch (input) {
                // Create a new course
                case "C":
                case "create course":
                    if (course == null) {
                        course = new Course();
                    }
                    course.addNewCourse(connection, "Java Advanced 2", "New course", "2017.01.10", "2017.02.10", 15);
                    break;

                // Show a course
                case "S":
                    System.out.print("Enter course ID: ");
                    id1 = scanner.nextLine();
                    if (course == null) {
                        course = new Course();
                    }
                    if (id1 != null) {
                        course.getCourse(connection, Integer.parseInt(id1));
                    }
                    break;
                case "SHOW COURSE":
                    if (course == null) {
                        course = new Course();
                    }
                    if (id1 != null) {
                        course.getCourse(connection, Integer.parseInt(id1));
                    }
                    break;

                // Show all courses
                case "A":
                case "SHOW COURSES":
                    if (course == null) {
                        course = new Course();
                    }
                    course.getAllCourses(connection);
                    break;

                // Show all students on the course
                case "E":
                    System.out.print("Enter course ID: ");
                    id1 = scanner.nextLine();
                    if (course == null) {
                        course = new Course();
                    }
                    if (id1 != null) {
                        course.getAllCourseStudents(connection, Integer.parseInt(id1));
                    }
                    break;
                case "SHOW STUDENTS":
                    if (course == null) {
                        course = new Course();
                    }
                    if (id1 != null) {
                        course.getAllCourseStudents(connection, Integer.parseInt(id1));
                    }
                    break;

                // Show a course journal
                case "O":
                    break;
                case "SHOW JOURNAL":
                    break;

                // Save a course journal to a file
                case "P":
                    break;
                case "SAVE JOURNAL":
                    break;

                // Create a new student
                case "B":
                case "CREATE STUDENT":
                    if (student == null) {
                        student = new Student();
                    }
                    System.out.print("Enter first name: ");
                    firstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    lasttName = scanner.nextLine();
                    System.out.print("Enter age: ");
                    age = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter courses: ");
                    courses = scanner.nextLine();
                    student.addNewStudent(connection, firstName, lasttName, age, courses);
                    break;

                // Exit programm
                case "Q":
                    System.out.println("Quit. Buy!");
                    break;

                // Incorrect choice
                default:
                    System.out.println("Incorrect input!");
                    break;
            }


        } while (!input.equals("Q"));


//        String strNormalSize = "\033[0;0m";
//        String strBoldSize = "\033[0;1m";
//        System.out.println (strNormalSize + "My Name is Anthony Gonsalves" + strBoldSize);}
    }
}