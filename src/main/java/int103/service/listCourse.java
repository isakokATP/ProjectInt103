package int103.service;

import java.sql.*;
import java.util.Scanner;

public class listCourse {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/int103";
    private static final String username = "root";
    private static final String password = "Kira5005.";

    public static void list(){
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet courses = statement.executeQuery("SELECT course_id, course_name FROM courses")){
            while (courses.next()){
                String course_id = courses.getString("course_id");
                String course_name = courses.getString("course_name");
                System.out.println("course id :" + course_id + ", course name :" + course_name);
            }
            System.out.println("connected successfully");
        } catch (SQLException e) {
            System.out.println("Can't connected course table " + e.getMessage());
        }
    }
}
