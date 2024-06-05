package int103.repositories;

import int103.entities.Course;
import int103.entities.Student;
import int103.exceptions.CustomException;

import java.util.List;
public interface StorageStrategy {
    List<Student> getAllStudents() throws CustomException;
    void addCourse(String courseId, String courseName) throws CustomException;
    List<Course> getAllCourses() throws CustomException;
    void registerStudentForCourse(long studentId, String courseId) throws CustomException;
    List<Course> getCoursesForStudent(long studentId) throws CustomException;
    List<Student> getStudentsForCourse(String courseId) throws CustomException;

}
