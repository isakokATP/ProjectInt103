package int103.service;

import java.sql.*;
import java.util.Scanner;

public class EditCourse {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/int103";
    private static final String username = "root";
    private static final String password = "Kira5005.";
    private static final Scanner scanner = new Scanner(System.in);


    public static void Edit(){
        System.out.print("Enter courseID for Edit: ");
        String courseID = scanner.next();
        scanner.useDelimiter("\\n");

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM courses WHERE course_id = ?");
             PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE courses SET course_name = ? WHERE course_id = ?")){

            preparedStatement.setString(1, courseID);
//          System.out.println(preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    do {
                        String courseId = resultSet.getString("course_id");
                        String courseName = resultSet.getString("course_name");
                        System.out.println("course_ID : " + courseId + "\n" + "course_Name : " + courseName);
                    } while (resultSet.next());
                } else {
                    System.out.println("No course found with the given courseID.");
                }
            }

            System.out.print("Enter courseName for Change: ");
            String courseName = scanner.next();

            preparedStatement2.setString(1, courseName);
            preparedStatement2.setString(2, courseID);

            int rowAdd = preparedStatement2.executeUpdate();
            if (rowAdd > 0) {
                System.out.println("Course has been updated");
            } else {
                System.out.println("Course has not been updated");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }

}
