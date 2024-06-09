package int103.repositories.database;

import int103.entities.Course;
import int103.entities.Student;
import int103.exceptions.CustomException;
import int103.repositories.StorageStrategy;

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
    public Student getStudentById(long studentId) throws CustomException {
        return null;
    }

    @Override
    public void addStudent(long studentId, String firstName, String lastName, String email) throws CustomException {
        if (studentId <= 0) {
            throw new CustomException("Student ID must be greater than 0");
        }
        if (firstName == null || firstName.isEmpty() && lastName == null || lastName.isEmpty()) {
            throw new CustomException("Student name cannot be empty or null");
        }
        if (email == null || email.isEmpty()) {
            throw new CustomException("Student email cannot be empty or null");
        }

        String sql = "INSERT INTO students (student_id, first_name, last_name, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, studentId);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException("Error adding student to database", e);
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
    public Course getCourseById(String courseId) throws CustomException {
        return null;
    }

    @Override
    public void addCourse(String courseId, String courseName) throws CustomException {
        if (courseId == null || courseId.isEmpty() && (courseName == null || courseName.isEmpty())) {
            throw new CustomException("Course id or name cannot be empty");
        }
        String sql = "INSERT INTO courses (course_id, course_name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, courseId);
            stmt.setString(2, courseName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException("Error adding course to database", e);
        }
    }

    @Override
    public void editCourse(String courseId) throws CustomException {
//        if (courseId == null || courseId.trim().isEmpty()) {
//            throw new CustomException("Course ID cannot be empty or null");
//        }
//
//        String sql = "UPDATE courses SET course_name = ? WHERE course_id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, newCourseName);
//            stmt.setString(2, courseId);
//            int rowsUpdated = stmt.executeUpdate();
//            if (rowsUpdated == 0) {
//                throw new CustomException("Course not found");
//            }
//        } catch (SQLException e) {
//            throw new CustomException("Error editing course in database", e);
//        }
    }

    @Override
    public void deleteCourse(String courseId) throws CustomException {
        if (courseId == null || courseId.trim().isEmpty()) {
            throw new CustomException("Course ID cannot be empty or null");
        }

        String sql = "DELETE FROM courses WHERE course_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, courseId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new CustomException("Course not found");
            }
        } catch (SQLException e) {
            throw new CustomException("Error deleting course from database", e);
        }
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