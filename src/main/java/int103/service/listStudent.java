package int103.service;

import java.sql.*;
import java.util.Scanner;

public class listStudent {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/int103";
    private static final String username = "root";
    private static final String password = "Kira5005.";

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
}
