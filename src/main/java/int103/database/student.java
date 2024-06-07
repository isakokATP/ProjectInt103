package int103.database;

import java.sql.*;
import java.util.Scanner;

public class student {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/int103";
    private static final String username = "root";
    private static final String password = "Kira5005.";
    private static final Scanner scanner = new Scanner(System.in);

    public static void list(){
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet students = statement.executeQuery("SELECT student_id, first_name, last_name, email FROM students")){

            while (students.next()){
                Long student_id = students.getLong("student_id");
                String first_name = students.getString("first_name");
                String last_name = students.getString("last_name");
                String email = students.getString("email");
                System.out.println("student id :" + student_id + "first_name :" + first_name + ", last_name :" + last_name + ", email :" + email);
            }
            System.out.println("list all students");
        } catch (SQLException e){
            System.out.println("Can't connection");
        }
    }

    public static void info(){
        System.out.print("Enter your student ID : ");
        Long stdId = scanner.nextLong();

        try(Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT r.student_id, r.course_id, c.course_name FROM registration r JOIN courses c ON r.course_id = c.course_id WHERE r.student_id = ?")) {

            statement.setLong(1, stdId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String studentId = resultSet.getString("student_id");
                String courseId = resultSet.getString("course_id");
                String courseName = resultSet.getString("course_name");
                System.out.println("studentId :" + studentId + "courseID :" + courseId + "courseName :" + courseName);
            }
            System.out.println("Successfully");
        } catch (SQLException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }
}
