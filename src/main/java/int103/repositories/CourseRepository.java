package int103.repositories;

import int103.entities.Course;
//import int103.exceptions.CustomException;
import int103.exceptions.DatabaseException;
import int103.exceptions.NotFoundException;

import java.util.List;

public interface CourseRepository {
    Course getCourseById(String courseId) throws NotFoundException;

    void addCourse(String courseId, String courseName) throws NotFoundException, DatabaseException;

    List<Course> getAllCourses() throws NotFoundException, DatabaseException;

    void editCourse(String courseId, String courseName) throws  NotFoundException, DatabaseException;

    void deleteCourse(String courseId) throws NotFoundException, DatabaseException;
}
