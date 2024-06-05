package int103.service;

import java.sql.*;
import java.util.Scanner;
public class studentInfo {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/int103";
    private static final String username = "root";
    private static final String password = "Kira5005.";
    private static final Scanner scanner = new Scanner(System.in);

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
