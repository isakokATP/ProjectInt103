package int103.repositories;

import int103.entities.Course;
import int103.exceptions.DatabaseException;
import int103.exceptions.NotFoundException;

import java.util.List;

public interface RegistrationRepository {
    void registerStudentForCourse(long studentId, String courseId) throws NotFoundException, DatabaseException;
    List<Course> getCoursesForStudent(long studentId) throws NotFoundException, DatabaseException;
    void unregisterStudentFromCourse(long studentId, String courseId) throws NotFoundException, DatabaseException;
}
