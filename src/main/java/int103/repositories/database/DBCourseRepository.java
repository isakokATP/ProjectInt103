package int103.repositories.database;

import int103.entities.Course;
import int103.entities.Student;
import int103.exceptions.CustomException;
import int103.repositories.CourseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBCourseRepository implements CourseRepository {
    private final Connection connection;

    public DBCourseRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Course> getAllCourses() throws CustomException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                courses.add(new Course(
                        rs.getString("course_id"),
                        rs.getString("course_name")
                ));
            }
        } catch (SQLException e) {
            throw new CustomException("Error retrieving courses from database", e);
        }
        return courses;
    }

    @Override
    public Course getCourseById(String courseId) throws CustomException {
        return null;
    }

    @Override
    public void addCourse(String courseId, String courseName) throws CustomException {
        String sql = "INSERT INTO courses (course_id, course_name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, courseId);
            stmt.setString(2, courseName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException("Error adding course to database", e);
        }
    }
}
