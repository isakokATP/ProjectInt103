package int103.Connector;

import java.sql.*;
import java.util.Scanner;

public class doSomething extends ConnectionDB{
    static ConnectionDB connectionDB = new ConnectionDB();
    // Database URL
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/int103";
    // Database credentials
    private static final String username = "root";
    private static final String password = "Kira5005.";
    //Scanner
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        listAllStudent();
        addCourse();
        deleteCourse();
    }


    public static void addCourse() throws ClassNotFoundException, SQLException {
        System.out.print("Enter your courseId :");
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

    public static void deleteCourse(){
        System.out.print("Enter your courseId to delete: ");
        String deleteCourse = scanner.next();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM registration WHERE registration_id = ?")){

            statement.setString(1, deleteCourse);

            int deleted = statement.executeUpdate();
            if (deleted > 0){
                System.out.println("your courseId has been deleted.");
            } else {
                System.out.println("Can't delete.");
            }
        } catch (SQLException e){
            System.out.println("Something went wrong" + e.getMessage());
        }
    }

    public static void listAllStudent(){
        try (Connection connection = DriverManager.getConnection(String.valueOf(connectionDB));
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