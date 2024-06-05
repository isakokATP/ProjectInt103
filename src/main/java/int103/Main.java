package int103;

import int103.Connector.ConnectionDB;
import int103.Entities.Course;
import int103.Entities.Register;
import int103.Entities.Students;
import  int103.Connector.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        // Database URL
//        String jdbcUrl = "jdbc:mysql://localhost:3306/int103";
//        // Database credentials
//        String username = "root";
//        String password = "Kira5005.";
//
//        // Using try-with-resources to ensure resources are closed
//        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery("SELECT registration_id, student_id, course_id FROM registration")) {
//
//            while (resultSet.next()) {
//                int regis_id = resultSet.getInt("registration_id");
//                String student_id = resultSet.getString("student_id");
//                String course_id = resultSet.getString("student_id");
//                System.out.println("registration ID: " + regis_id + ", student_ID: " + student_id + ", course_id" + course_id);
//            }
//            System.out.println("Connected successfully");
//        } catch (SQLException e) {
//            System.out.println("cant connected regis table" + e.getMessage());
//        }
//
//        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
//             Statement statement = connection.createStatement();
//             ResultSet students = statement.executeQuery("SELECT student_id, first_name, last_name, email FROM students")){
//
//            while (students.next()) {
//                Long student_id = students.getLong("student_id");
//                String first_name = students.getString("first_name");
//                String last_name = students.getString("last_name");
//                String email = students.getString("email");
//                System.out.println("student id :" + student_id + "first_name :" + first_name + ", last_name :" + last_name + ", email :" + email);
//            }
//            System.out.println("connected successfully");
//        } catch (SQLException e) {
//            System.out.println("cant connected student table " + e.getMessage());
//        }
//
//        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
//        Statement statement = connection.createStatement();
//        ResultSet courses = statement.executeQuery("SELECT course_id, course_name FROM courses")){
//            while (courses.next()){
//                String course_id = courses.getString("course_id");
//                String course_name = courses.getString("course_name");
//                System.out.println("course id :" + course_id + ", course name :" + course_name);
//            }
//            System.out.println("connected successfully");
//        } catch (SQLException e) {
//            System.out.println("cant connected course table " + e.getMessage());
//        }
//        List<Register> registrations = ConnectionDB.getRegistrations();
//        for (Register registration : registrations) {
//            System.out.println(registration);
//        }
//
//        List<Students> students = ConnectionDB.getStudents();
//        for (Students student : students) {
//            System.out.println(student);
//        }
//
//        List<Course> courses = ConnectionDB.getCourses();
//        for (Course course : courses) {
//            System.out.println(course);
//        }
        try {
            doSomething.deleteCourse();
            doSomething.listAllStudent();
            doSomething.addCourse();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("cant use" + e.getMessage());
        }
    }
}