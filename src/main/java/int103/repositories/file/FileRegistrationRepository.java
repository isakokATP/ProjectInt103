package int103.repositories.file;

import int103.entities.Course;
import int103.entities.Register;
import int103.exceptions.CustomException;
import int103.repositories.CourseRepository;
import int103.repositories.RegistrationRepository;
import int103.repositories.StudentRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRegistrationRepository implements RegistrationRepository {
    private static final String REGISTRATIONS_FILE = "registrations.dat";
    private final List<Register> registrations = new ArrayList<>();
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public FileRegistrationRepository(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        load();
    }

    @Override
    public void registerStudentForCourse(long studentId, String courseId) throws CustomException {
        registrations.add(new Register(registrations.size() + 1, studentId, courseId));
        save();
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

    private void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(REGISTRATIONS_FILE))) {
            registrations.addAll((List<Register>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new CustomException("Error loading registrations from file", e);
        }
    }

    private void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(REGISTRATIONS_FILE))) {
            oos.writeObject(registrations);
        } catch (IOException e) {
            throw new CustomException("Error saving registrations to file", e);
        }
    }
}
