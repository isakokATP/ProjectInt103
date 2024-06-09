package int103.services;

import int103.entities.Course;
import int103.exceptions.DatabaseException;
import int103.exceptions.NotFoundException;
import int103.repositories.RegistrationRepository;

import java.util.List;

public class RegistrationService {
    private final RegistrationRepository registrationRepo;

    public RegistrationService(RegistrationRepository registrationRepo) {
        this.registrationRepo = registrationRepo;
    }

    public void registerStudentForCourse(long studentId, String courseId) throws NotFoundException, DatabaseException {
        registrationRepo.registerStudentForCourse(studentId, courseId);
    }

    public List<Course> getCoursesForStudent(long studentId) throws NotFoundException, DatabaseException {
        return registrationRepo.getCoursesForStudent(studentId);
    }

    public void unregisterStudentFromCourse(long studentId, String courseId) throws NotFoundException, DatabaseException{
        registrationRepo.unregisterStudentFromCourse(studentId, courseId);
    }


}
