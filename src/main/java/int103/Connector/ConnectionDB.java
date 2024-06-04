package int103.Connector;

import int103.Entities.Course;
import int103.Entities.Register;
import int103.Entities.Students;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDB {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/int103";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Kira5005.";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static List<Register> getRegistrations() {
        List<Register> registrations = new ArrayList<>();
        String query = "SELECT registration_id, student_id, course_id FROM registration";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int regisId = resultSet.getInt("registration_id");
                String studentId = resultSet.getString("student_id");
                String courseId = resultSet.getString("course_id");
                registrations.add(new Register(regisId, studentId, courseId));
            }
            System.out.println("Connected successfully to the registration table.");
        } catch (SQLException e) {
            System.out.println("Can't connect to registration table: " + e.getMessage());
        }
        return registrations;
    }

    public static List<Students> getStudents() {
        List<Students> students = new ArrayList<>();
        String query = "SELECT student_id, first_name, last_name, email FROM students";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                long studentId = resultSet.getLong("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                students.add(new Students(studentId, firstName, lastName, email));
            }
            System.out.println("Connected successfully to the student table.");
        } catch (SQLException e) {
            System.out.println("Can't connect to student table: " + e.getMessage());
        }
        return students;
    }

    public static List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT course_id, course_name FROM courses";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String courseId = resultSet.getString("course_id");
                String courseName = resultSet.getString("course_name");
                courses.add(new Course(courseId, courseName));
            }
            System.out.println("Connected successfully to the course table.");
        } catch (SQLException e) {
            System.out.println("Can't connect to course table: " + e.getMessage());
        }
        return courses;
    }
}
