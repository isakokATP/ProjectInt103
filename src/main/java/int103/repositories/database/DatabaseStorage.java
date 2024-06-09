package int103.repositories.database;

import int103.entities.Course;
import int103.entities.Student;
//import int103.exceptions.CustomException;
import int103.exceptions.DatabaseException;
import int103.exceptions.InvalidException;
import int103.exceptions.NotFoundException;
import int103.repositories.StorageStrategy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseStorage implements StorageStrategy {
    private final Connection connection;

    public DatabaseStorage(Connection connection) {
        this.connection = connection;
    }

    public boolean isStudentIdExist(long studentId) throws DatabaseException {
        String sql = "SELECT COUNT(*) FROM students WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error checking student ID in database", e);
        }
        return false;
    }

    @Override
    public void addStudent(long studentId, String firstName, String lastName, String email) throws InvalidException, DatabaseException{
        String studentIdStr = String.valueOf(studentId);
        if (studentIdStr.length() != 11 || !studentIdStr.matches("\\d{11}")) {
            throw new InvalidException("Student ID must be an 11-digit number");
        }
        if (firstName == null || firstName.isEmpty() && lastName == null || lastName.isEmpty()) {
            throw new InvalidException("Student name cannot be empty or null");
        }
        if (email == null || email.isEmpty()) {
            throw new InvalidException("Student email cannot be empty or null");
        }
        if (isStudentIdExist(studentId)) {
            throw new InvalidException("The student ID already exists in the database");
        }

        String sql = "INSERT INTO students (student_id, first_name, last_name, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, studentId);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding student to database", e);
        }
    }


    @Override
    public List<Student> getAllStudents() throws DatabaseException, NotFoundException {
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
            throw new DatabaseException("Error retrieving students from database", e);
        }
        return students;
    }

    @Override
    public Student getStudentById(long studentId) throws NotFoundException{
        return null;
    }

    @Override
    public void deleteStudent(Long studentId) throws NotFoundException, DatabaseException {
        if (studentId == null || studentId.describeConstable().isEmpty()){
            throw new NotFoundException("Can not delete");
        }
        String sql = "DELETE FROM students WHERE student_id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, studentId);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new DatabaseException("Something went wrong", e);
        }
    }

    @Override
    public List<Course> getAllCourses() throws NotFoundException, DatabaseException {
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
            throw new DatabaseException("Error retrieving courses from database", e);
        }
        return courses;
    }

    @Override
    public Course getCourseById(String courseId) throws NotFoundException {
        return null;
    }

    private boolean isCourseIdExist(String courseId) throws DatabaseException {
        String sql = "SELECT COUNT(*) FROM courses WHERE course_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error checking course ID in database", e);
        }
        return false;
    }

    @Override
    public void addCourse(String courseId, String courseName) throws DatabaseException, InvalidException {
        if (courseId == null || courseId.isEmpty() && courseName == null || courseName.isEmpty()) {
            throw new InvalidException("Course id or name cannot be empty");
        }
        if (isCourseIdExist(courseId)) {
            throw new InvalidException("The course ID already exists in the database");
        }

        String sql = "INSERT INTO courses (course_id, course_name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, courseId);
            stmt.setString(2, courseName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error adding course to database", e);
        }
    }

    @Override
    public void editCourse(String courseId, String courseName) throws NotFoundException, DatabaseException{
        if (courseId == null || courseId.isEmpty()){
            throw new NotFoundException("courseId is not null");
        }
        if (!isCourseIdExist(courseId)) {
            throw new NotFoundException("No course found with the given course ID.");
        }
        String sqlView = "SELECT * FROM courses WHERE course_id = ?";
        String sql = "UPDATE courses SET course_name = ? WHERE course_id = ?";
        try(PreparedStatement stmt1 = connection.prepareStatement(sqlView);
            PreparedStatement stmt2 = connection.prepareStatement(sql)){
            stmt1.setString(1, courseId);

            stmt2.setString(1, courseName);
            stmt2.setString(2, courseId);
            stmt2.executeUpdate();
        } catch (SQLException e){
            throw new DatabaseException("Error something went wrong.", e);
        }
    }

    @Override
    public void deleteCourse(String courseId) throws NotFoundException, DatabaseException {
        if (courseId == null || courseId.isEmpty()){
            throw new NotFoundException("courseId is not null");
        }
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, courseId);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new DatabaseException("Error something went wrong", e);
        }
    }

    @Override
    public void registerStudentForCourse(long studentId, String courseId) throws NotFoundException, DatabaseException {
        try {
            if (!isStudentIdExist(studentId)) {
                throw new NotFoundException("Student ID not found");
            }

            if (isStudentAlreadyRegistered(studentId, courseId)) {
                throw new DatabaseException("Student is already registered for this course");
            }

            String sql = "INSERT INTO registration (student_id, course_id) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setLong(1, studentId);
                stmt.setString(2, courseId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new DatabaseException("Error registering student for course in database", e);
            }
        } catch (NotFoundException e) {
            throw new NotFoundException("Student ID not found");
        }
    }

    private boolean isStudentAlreadyRegistered(long studentId, String courseId) throws DatabaseException {
        String sql = "SELECT COUNT(*) FROM registration WHERE student_id = ? AND course_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, studentId);
            stmt.setString(2, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error checking student registration for course in database", e);
        }
        return false;
    }

    @Override
    public List<Course> getCoursesForStudent(long studentId) throws NotFoundException,DatabaseException {
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
                            rs.getString("course_name")));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving courses for student from database", e);
        }
        return courses;
    }

    @Override
    public void unregisterStudentFromCourse(long studentId, String courseId) throws NotFoundException, DatabaseException{
        String sql = "DELETE FROM registration WHERE student_id = ? AND course_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, studentId);
            stmt.setString(2, courseId);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new NotFoundException("No registration found for student with id " + studentId + " and course id " + courseId);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error unregistering student from course in database", e);
        }
    }
}