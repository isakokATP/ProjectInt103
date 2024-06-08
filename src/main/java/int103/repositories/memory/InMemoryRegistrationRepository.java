package int103.repositories.memory;

import int103.entities.Course;
import int103.entities.Register;
import int103.exceptions.CustomException;
import int103.repositories.CourseRepository;
import int103.repositories.RegistrationRepository;
import int103.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRegistrationRepository implements RegistrationRepository {
    private final List<Register> registrations = new ArrayList<>();
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public InMemoryRegistrationRepository(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void registerStudentForCourse(long studentId, String courseId) throws CustomException {
        registrations.add(new Register(registrations.size() + 1, studentId, courseId));
    }

    @Override
    public List<Course> getCoursesForStudent(long studentId) throws CustomException {
        if (studentRepository.getStudentById(studentId) == null) {
            throw new CustomException("Student not found.");
        }
        return registrations.stream()
                .filter(registration -> registration.getStudentId() == studentId)
                .map( registration -> courseRepository.getCourseById(registration.getCourseId()))
                .toList();
    }
}
