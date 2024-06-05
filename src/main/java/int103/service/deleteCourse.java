package int103.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class deleteCourse {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/int103";
    private static final String username = "root";
    private static final String password = "Kira5005.";
    private static final Scanner scanner = new Scanner(System.in);

    public static void delete(){
        System.out.print("Enter your courseId to delete: ");
        String deleteCourse = scanner.next();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM registration WHERE registration_id = ?")) {

            statement.setString(1, deleteCourse);

            int deleted = statement.executeUpdate();
            if (deleted > 0) {
                System.out.println("your courseId has been deleted.");
            } else {
                System.out.println("Can't delete.");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }
}
