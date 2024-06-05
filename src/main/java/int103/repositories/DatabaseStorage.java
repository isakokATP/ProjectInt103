package int103.repositories;

import int103.entities.Course;
import int103.entities.Student;
import int103.exceptions.CustomException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseStorage implements StorageStrategy {
    private final Connection connection;

    public DatabaseStorage(Connection connection) {
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
    public void addCourse(String courseId, String courseName) throws CustomException {
        String sql = "INSERT INTO courses (courseId, courseName) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, courseId);
            stmt.setString(2, courseName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException("Error adding course to database", e);
        }
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

    @Override
    public List<Student> getStudentsForCourse(String courseId) throws CustomException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT s.student_id, s.first_name, s.last_name, s.email " +
                "FROM students s " +
                "JOIN registration r ON s.student_id = r.student_id " +
                "WHERE r.course_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                            rs.getInt("student_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new CustomException("Error retrieving students for course from database", e);
        }
        return students;
    }
}

