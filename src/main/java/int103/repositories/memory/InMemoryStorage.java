package int103.repositories.memory;

import int103.entities.Course;
import int103.entities.Register;
import int103.entities.Student;
import int103.exceptions.CustomException;
import int103.repositories.CourseRepository;
import int103.repositories.StorageStrategy;
import int103.repositories.StudentRepository;

import java.util.*;

public class InMemoryStorage implements StorageStrategy {
    private final Map<Long, Student> students = new HashMap<>();
    private final Map<String, Course> courses = new HashMap<>();
    private final List<Register> registrations = new ArrayList<>();

    @Override
    public List<Student> getAllStudents() throws CustomException {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student getStudentById(long studentId) throws CustomException {
        return students.get(studentId);
    }

    @Override
    public List<Course> getAllCourses() throws CustomException {
        return new ArrayList<>(courses.values());
    }

    @Override
    public Course getCourseById(String courseId) throws CustomException {
        return courses.get(courseId);
    }

    @Override
    public void addCourse(String courseId, String courseName) throws CustomException {
        courses.put(courseId, new Course(courseId, courseName));
    }

    @Override
    public void registerStudentForCourse(long studentId, String courseId) throws CustomException {
        registrations.add(new Register(registrations.size() + 1, studentId, courseId));
    }

    @Override
    public List<Course> getCoursesForStudent(long studentId) throws CustomException {
        if (!students.containsKey(studentId)) {
            throw new CustomException("Student not found.");
        }

        return registrations.stream()
                .filter(registration -> registration.getStudentId() == studentId)
                .map( registration -> courses.get(registration.getCourseId()))
                .toList();
    }
}