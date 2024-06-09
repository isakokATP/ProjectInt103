package int103.repositories;

import int103.exceptions.CustomException;

public interface StorageStrategy extends StudentRepository, CourseRepository, RegistrationRepository {
    void unregisterStudentFromCourse(long studentId, String courseId) throws CustomException;
}
