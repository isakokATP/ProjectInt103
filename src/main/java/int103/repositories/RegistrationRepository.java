package int103.repositories;

import int103.entities.Course;
import int103.exceptions.CustomException;

import java.util.List;

public interface RegistrationRepository {
    void registerStudentForCourse(long studentId, String courseId) throws CustomException;
    List<Course> getCoursesForStudent(long studentId) throws CustomException;

    void unregisterStudentFromCourse(long studentId, String courseId) throws CustomException;
}
