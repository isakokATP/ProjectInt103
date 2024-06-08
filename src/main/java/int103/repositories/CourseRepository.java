package int103.repositories;

import int103.entities.Course;
import int103.exceptions.CustomException;

import java.util.List;

public interface CourseRepository {
    Course getCourseById(String courseId) throws CustomException;

    void addCourse(String courseId, String courseName) throws CustomException;
    List<Course> getAllCourses() throws CustomException;

}
