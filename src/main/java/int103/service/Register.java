package int103.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Register {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/int103";
    private static final String username = "root";
    private static final String password = "Kira5005.";
    private static final Scanner scanner = new Scanner(System.in);

    public static void add(){
        System.out.print("Enter your ID :");
        Long stdId = scanner.nextLong();

        System.out.print("Enter your CourseId : ");
        String courseID = scanner.next();

        try(Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO registration VALUES (?, ?)")){

            statement.setLong(1, stdId);
            statement.setString(1, courseID);

            int rowAdded = statement.executeUpdate();
            if (rowAdded > 0){
                System.out.println("Welcome");
            } else {
                System.out.println("Can't Register");
            }
        } catch (SQLException e){
            System.out.println("Something went wrong" + e.getMessage());
        }
    }
}
