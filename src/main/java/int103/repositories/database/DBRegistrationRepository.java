package int103.repositories.database;

import int103.entities.Course;
import int103.exceptions.CustomException;
import int103.repositories.RegistrationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBRegistrationRepository implements RegistrationRepository {
    private final Connection connection;

    public DBRegistrationRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void registerStudentForCourse(long studentId, String courseId) throws CustomException {
        String sql = "INSERT INTO registration (student_id, course_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, studentId);
            stmt.setString(2, courseId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException("Error registering student for course in database", e);
        }
    }

    @Override
    public List<Course> getCoursesForStudent(long studentId) throws CustomException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.course_id, c.course_name " +
                "FROM courses c " +
                "JOIN registration r ON c.course_id = r.course_id " +
                "WHERE r.student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    courses.add(new Course(
                            rs.getString("course_id"),
                            rs.getString("course_name")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new CustomException("Error retrieving courses for student from database", e);
        }
        return courses;
    }
}
