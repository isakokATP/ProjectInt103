package int103.repositories.database;

import int103.entities.Student;
import int103.exceptions.CustomException;
import int103.repositories.StudentRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBStudentRepository implements StudentRepository {
    private final Connection connection;

    public DBStudentRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Student> getAllStudents() throws CustomException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getLong("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            throw new CustomException("Error retrieving students from database", e);
        }
        return students;
    }

    @Override
    public Student getStudentById(long studentId) throws CustomException {
        return null;
    }
}
