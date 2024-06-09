package int103.database;

import java.sql.*;
import java.util.Scanner;

public class Course {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/int103";
    private static final String username = "root";
    private static final String password = "Kira5005.";
    private static final Scanner scanner = new Scanner(System.in);

    public static void addCourse(){
        System.out.print("Enter your courseId : ");
        String addCourse_id = scanner.next();

        System.out.print("Enter your courseName: ");
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

    public static void Edit(){
        System.out.print("Enter courseID for Edit: ");
        String courseID = scanner.next();


        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM courses WHERE course_id = ?");
             PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE courses SET course_name = ? WHERE course_id = ?")){



            preparedStatement.setString(1, courseID);
            System.out.println(preparedStatement);



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

    public static void delete(){
        System.out.print("Enter your registrationID to delete: ");
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
