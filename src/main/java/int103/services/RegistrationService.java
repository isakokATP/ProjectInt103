package int103.services;

import int103.entities.Course;
import int103.exceptions.CustomException;
import int103.repositories.RegistrationRepository;

import java.util.List;

public class RegistrationService {
    private final RegistrationRepository registrationRepo;

    public RegistrationService(RegistrationRepository registrationRepo) {
        this.registrationRepo = registrationRepo;
    }

    public void registerStudentForCourse(long studentId, String courseId) throws CustomException {
        registrationRepo.registerStudentForCourse(studentId, courseId);
    }

    public List<Course> getCoursesForStudent(long studentId) throws CustomException {
        return registrationRepo.getCoursesForStudent(studentId);
    }

}
