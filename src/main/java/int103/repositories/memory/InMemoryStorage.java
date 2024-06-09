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
    public void addStudent(long studentId, String firstName, String lastName, String email) throws CustomException {
        students.put(studentId, new Student(studentId, firstName, lastName, email));
    }

    @Override
    public void deleteStudent(Long studentId) throws CustomException {
        if (!students.containsKey(studentId)){
            throw new CustomException("Students not found");
        }
        students.remove(studentId);

        registrations.removeIf(registration -> registration.getStudentId().equals(studentId));
    }

    @Override
    public List<Student> getAllStudents() throws CustomException {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student getStudentById(long studentId) throws CustomException {
        return students.get(studentId);
    }

    @Override
    public void addCourse(String courseId, String courseName) throws CustomException {
        courses.put(courseId, new Course(courseId, courseName));
    }

    @Override
    public void editCourse(String courseId, String courseName) throws CustomException {
        Course course = courses.get(courseId);
        if (course == null) {
            throw new CustomException("Course not found");
        }
        course.setName(courseName);
    }

    @Override
    public void deleteCourse(String courseId) throws CustomException {
        if (!courses.containsKey(courseId)) {
            throw new CustomException("Course not found");
        }
        // Remove the course from the courses map
        courses.remove(courseId);

        // Also remove registrations related to this course
        registrations.removeIf(registration -> registration.getCourseId().equals(courseId));
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

    @Override
    public void unregisterStudentFromCourse(long studentId, String courseId) throws CustomException {
        if (!students.containsKey(studentId)) {
            throw new CustomException("Student not found");
        }
        if (!courses.containsKey(courseId)) {
            throw new CustomException("Course not found");
        }

        boolean removed = registrations.removeIf(registration ->
                registration.getStudentId() == studentId && registration.getCourseId().equals(courseId)
        );

        if (!removed) {
            throw new CustomException("Registration not found for student " + studentId + " in course " + courseId);
        }
    }
}