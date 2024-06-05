package int103.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddCourse {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/int103";
    private static final String username = "root";
    private static final String password = "Kira5005.";
    private static final Scanner scanner = new Scanner(System.in);

    public static void addCourse(){
        System.out.print("Enter your courseId : ");
        String addCourse_id = scanner.next();

        System.out.print("Enter your courseName:");
        String addCourse_name = scanner.next();

        try(Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO courses values (?, ?)")){

            statement.setString(1,addCourse_id);
            statement.setString(2, addCourse_name);

            int rowAdded = statement.executeUpdate();
            if (rowAdded > 0){
                System.out.println("you course has been add.");
            } else {
                System.out.println("you course already exist.");
            }
        } catch (SQLException e){
            System.out.println("Something went wrong" + e.getMessage());
        }
    }
}
