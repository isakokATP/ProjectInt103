package int103;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Database URL
        String jdbcUrl = "jdbc:mysql://localhost:3306/int103";
        // Database credentials
        String username = "root";
        String password = "Kira5005.";

        // Using try-with-resources to ensure resources are closed
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT registration_id, student_id, course_id FROM registration")) {

            while (resultSet.next()) {
                int regis_id = resultSet.getInt("registration_id");
                String student_id = resultSet.getString("student_id");
                String course_id = resultSet.getString("student_id");
                System.out.println("registration ID: " + regis_id + ", student_ID: " + student_id + ", course_id" + course_id);
            }

            System.out.println("Connected to the database successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
