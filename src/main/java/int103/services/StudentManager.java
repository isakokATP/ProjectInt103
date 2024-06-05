//package int103.services;
//
//import int103.entities.Students;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class StudentManager {
//    private Connection connection;
//
//    public StudentManager(Connection connection) {
//        this.connection = connection;
//    }
//
//    public void addStudent(String firstName, String lastName, String email) throws SQLException {
//        String sql = "INSERT INTO students (first_name, last_name, email) VALUES (?, ?, ?)";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, firstName);
//            statement.setString(2, lastName);
//            statement.setString(3, email);
//            statement.executeUpdate();
//        }
//    }
//
//    public List<Student> getAllStudents() throws SQLException {
//        List<Students> students = new ArrayList<>();
//        String sql = "SELECT * FROM students";
//        try (PreparedStatement statement = connection.prepareStatement(sql);
//             ResultSet resultSet = statement.executeQuery()) {
//            while (resultSet.next()) {
//                Student student = new Student();
//                student.setStudentId(resultSet.getInt("studentId"));
//                student.setFirstName(resultSet.getString("firstName"));
//                student.setLastName(resultSet.getString("lastName"));
//                student.setEmail(resultSet.getString("email"));
//                student.add(student);
//            }
//        }
//        return student;
//    }
//
//    public Student getStudent(int studentId) throws SQLException {
//        String sql = "SELECT * FROM students WHERE student_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, studentId);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    Student student = new Student();
//                    student.setStudentId(resultSet.getInt("student_id"));
//                    student.setFirstName(resultSet.getString("first_name"));
//                    student.setLastName(resultSet.getString("last_name"));
//                    student.setEmail(resultSet.getString("email"));
//                    return student;
//                }
//            }
//        }
//        return null;
//    }
//}
