package com.brainacad.java.learner.UI;

import com.brainacad.java.learner.DB.*;

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
                "---\n" +
                "Show a course journal: enter \"show journal <cID>\" or [O]\n" +
                "Save a course journal to a file: enter \"save journal <cID>\" or [P]\n" +
                "---\n" +
                "Create a new student: enter \"create student\" or [B]\n" +
                "Add a student to the course: enter \"transfer student <sID> add <cID>\" or [D]\n" +
                "Remove a student from the course: enter \"transfer student <sID> remove <cID>\" or [Z]\n" +
                "Show a student: enter \"show student <sID>\" or [R]\n" +
                "Show all students: enter \"show all students\" or [G]\n" +
                "---\n" +
                "Create a new trainer: enter \"create trainer\" or [Y]\n" +
                "Show a trainer: enter \"show trainer <tID>\" or [U]\n" +
                "---\n" +
                "Create a new task: enter \"create task\" or [I]\n" +
                "Show all tasks: enter \"show tasks\" or [T]\n" +
                "---\n" +
                "Exit program: enter \"exit\" or [Q]\n" +
                "==============================\n");
    }

    // User input
    public static void Input(Connection connection) {

        Coach coach = null;
        Course course = null;
        Student student = null;
        Task task = null;
        Mark mark = null;
        String input;
        Pattern patternInput = Pattern.compile("\\w*\\s*\\w*\\s*(\\d*)\\s*\\w*\\s*(\\d*)");
        Pattern patternCourses = Pattern.compile("(\\d*)\\s*(\\d*)\\s*(\\d*)");
        Matcher matcherInput, matcherCourses;
        String id1 = null;
        String id2 = null;
        String firstName, lastName, courses, courseName, courseDescription, courseStartDate, courseFinishDate, taskName;
        String[] coursess = new String[10];
        int age, courseDays;

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
            if (input.contains("SHOW STUDENT")) {
                input = "SHOW STUDENT";
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
                    System.out.print("Enter course name: ");
                    courseName = scanner.nextLine();
                    System.out.print("Enter course description: ");
                    courseDescription = scanner.nextLine();
                    System.out.print("Enter course start date: ");
                    courseStartDate = scanner.nextLine();
                    System.out.print("Enter course start date: ");
                    courseFinishDate = scanner.nextLine();
                    System.out.print("Enter course days: ");
                    courseDays = Integer.parseInt(scanner.nextLine());
                    if (course == null) {
                        course = new Course();
                    }
                    course.addNewCourse(connection, courseName, courseDescription, courseStartDate, courseFinishDate, courseDays);
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
                    System.out.print("Enter course ID: ");
                    id1 = scanner.nextLine();
                    if (mark == null) {
                        mark = new Mark();
                    }
                    if (id1 != null) {
                        mark.getMarksByCourse(connection, Integer.parseInt(id1));
                    }
                    break;
                case "SHOW JOURNAL":
                    if (mark == null) {
                        mark = new Mark();
                    }
                    mark.getMarksByCourse(connection, Integer.parseInt(id1));
                    break;

                // Save a course journal to a file
                case "P":
                    System.out.print("Enter course ID: ");
                    id1 = scanner.nextLine();
                    if (mark == null) {
                        mark = new Mark();
                    }
                    mark.saveJournalToFile(connection, Integer.parseInt(id1));
                    break;
                case "SAVE JOURNAL":
                    if (mark == null) {
                        mark = new Mark();
                    }
                    mark.saveJournalToFile(connection, Integer.parseInt(id1));
                    break;

                // Create a new student
                case "B":
                case "CREATE STUDENT":
                    System.out.print("Enter student first name: ");
                    firstName = scanner.nextLine();
                    System.out.print("Enter student last name: ");
                    lastName = scanner.nextLine();
                    System.out.print("Enter student age: ");
                    age = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter courses: ");
                    courses = scanner.nextLine();
                    if (student == null) {
                        student = new Student();
                    }
                    student.addNewStudent(connection, firstName, lastName, age, courses);
                    break;

                // Add the student to the course
                case "D":
                    System.out.print("Enter student ID: ");
                    id1 = scanner.nextLine();
                    System.out.print("Enter course ID: ");
                    id2 = scanner.nextLine();
                    if (student == null) {
                        student = new Student();
                    }
                    student.addStudentToCourse(connection, Integer.parseInt(id1), Integer.parseInt(id2));
                    break;
                case "MOVE STUDENT":
                    if (student == null) {
                        student = new Student();
                    }
                    if ((id1 != null) && (id2 != null)) {
                        student.addStudentToCourse(connection, Integer.parseInt(id1), Integer.parseInt(id2));
                    }
                    break;

                // Remove the student from the course
                case "Z":
                    System.out.print("Enter student ID: ");
                    id1 = scanner.nextLine();
                    System.out.print("Enter course ID: ");
                    id2 = scanner.nextLine();
                    if (student == null) {
                        student = new Student();
                    }
                    student.removeStudentFromCourse(connection, Integer.parseInt(id1), Integer.parseInt(id2));
                    break;
                case "REMOVE STUDENT":
                    if (student == null) {
                        student = new Student();
                    }
                    if ((id1 != null) && (id2 != null)) {
                        student.removeStudentFromCourse(connection, Integer.parseInt(id1), Integer.parseInt(id2));
                    }
                    break;

                // Show student by ID
                case "R":
                    System.out.print("Enter student ID: ");
                    id1 = scanner.nextLine();
                    if (student == null) {
                        student = new Student();
                    }
                    if (id1 != null) {
                        student.getStudent(connection, Integer.parseInt(id1));
                    }
                    break;
                case "SHOW STUDENT":
                    if (student == null) {
                        student = new Student();
                    }
                    if (id1 != null) {
                        student.getStudent(connection, Integer.parseInt(id1));
                    }
                    break;

                case "G":
                case "SHOW ALL STUDENTS":
                    if (student == null) {
                        student = new Student();
                    }
                    student.getAllStudents(connection);
                    break;

                // Create a new trainer
                case "Y":
                case "CREATE TRAINER":
                    System.out.print("Enter trainer first name: ");
                    firstName = scanner.nextLine();
                    System.out.print("Enter trainer last name: ");
                    lastName = scanner.nextLine();
                    System.out.print("Enter course: ");
                    id1 = scanner.nextLine();
                    if (coach == null) {
                        coach = new Coach();
                    }
                    coach.addNewCoach(connection, firstName, lastName, Integer.parseInt(id1));
                    break;

                // Show trainer by ID
                case "U":
                    System.out.print("Enter a coach ID: ");
                    id1 = scanner.nextLine();
                    if (coach == null) {
                        coach = new Coach();
                    }
                    if (id1 != null) {
                        coach.getCoach(connection, Integer.parseInt(id1));
                    }
                    break;
                case "SHOW TRAINER":
                    if (coach == null) {
                        coach = new Coach();
                    }
                    if (id1 != null) {
                        coach.getCoach(connection, Integer.parseInt(id1));
                    }
                    break;

                // Create a new task
                case "I":
                case "CREATE TASK":
                    System.out.print("Enter task name: ");
                    taskName = scanner.nextLine();
                    System.out.print("Enter course ID for this task: ");
                    id1 = scanner.nextLine();
                    if (task == null) {
                        task = new Task();
                    }
                    task.addNewTask(connection, taskName, Integer.parseInt(id1));
                    break;

                // Show all tasks
                case "T":
                case "SHOW ALL TASKS":
                    if (task == null) {
                        task = new Task();
                    }
                    task.getAllTasks(connection);
                    break;

                // Exit programm
                case "Q":
                case "EXIT":
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