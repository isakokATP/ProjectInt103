package int103.repositories.memory;

import int103.entities.Course;
import int103.entities.Register;
import int103.entities.Student;
//import int103.exceptions.CustomException;
import int103.exceptions.InvalidException;
import int103.exceptions.NotFoundException;
import int103.repositories.CourseRepository;
import int103.repositories.StorageStrategy;
import int103.repositories.StudentRepository;

import java.util.*;

public class InMemoryStorage implements StorageStrategy {
    private final Map<Long, Student> students = new HashMap<>();
    private final Map<String, Course> courses = new HashMap<>();
    private final List<Register> registrations = new ArrayList<>();

    @Override
    public void addStudent(long studentId, String firstName, String lastName, String email) throws InvalidException {
        String studentIdStr = String.valueOf(studentId);
        if (studentIdStr.length() != 11 || !studentIdStr.matches("\\d{11}")) {
            throw new InvalidException("Student ID must be an 11-digit number");
        }
        if (firstName == null || firstName.isEmpty() && lastName == null || lastName.isEmpty()) {
            throw new InvalidException("Student name cannot be empty or null");
        }
        if (email == null || email.isEmpty()) {
            throw new InvalidException("Student email cannot be empty or null");
        }
        if (isStudentIdExist(studentId)) {
            throw new InvalidException("The student ID already exists in the memory");
        }
        students.put(studentId, new Student(studentId, firstName, lastName, email));
    }

    @Override
    public void deleteStudent(Long studentId) throws NotFoundException {
        if (!students.containsKey(studentId)){
            throw new NotFoundException("Students not found");
        }
        students.remove(studentId);

        registrations.removeIf(registration -> registration.getStudentId().equals(studentId));
    }

    @Override
    public List<Student> getAllStudents() throws NotFoundException {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student getStudentById(long studentId) throws NotFoundException {
        return students.get(studentId);
    }

    @Override
    public void addCourse(String courseId, String courseName) throws InvalidException {
        if (courseId.isEmpty() && courseName == null || courseName.isEmpty()){
            throw new InvalidException("Course is not null or empty.");
        }
        if (isCourseIdExist(courseId)) {
            throw new InvalidException("The course ID already exists in the memory");
        }
        courses.put(courseId, new Course(courseId, courseName));
    }

    @Override
    public void editCourse(String courseId, String courseName) throws NotFoundException {
        Course course = courses.get(courseId);
        if (course == null) {
            throw new NotFoundException("Course not found");
        }
        if (!isCourseIdExist(courseId)) {
            throw new NotFoundException("No course found with the given course ID.");
        }
        course.setName(courseName);
    }

    @Override
    public void deleteCourse(String courseId) throws NotFoundException {
        if (!courses.containsKey(courseId)) {
            throw new NotFoundException("Course not found");
        }
        courses.remove(courseId);
        registrations.removeIf(registration -> registration.getCourseId().equals(courseId));
    }

    @Override
    public List<Course> getAllCourses() throws NotFoundException{
        return new ArrayList<>(courses.values());
    }

    @Override
    public Course getCourseById(String courseId) throws NotFoundException {
        return courses.get(courseId);
    }

    @Override
    public void registerStudentForCourse(long studentId, String courseId) throws NotFoundException {
        if (!isStudentIdExist(studentId)) {
            throw new NotFoundException("Student ID not found");
        }
        if (registrations.stream().anyMatch(registration ->
                registration.getStudentId() == studentId && registration.getCourseId().equals(courseId))) {
            throw new NotFoundException("Student is already registered for this course");
        }
        registrations.add(new Register(registrations.size() + 1, studentId, courseId));
    }

    @Override
    public List<Course> getCoursesForStudent(long studentId) throws NotFoundException {
        if (!students.containsKey(studentId)) {
            throw new NotFoundException("Student not found.");
        }

        return registrations.stream()
                .filter(registration -> registration.getStudentId() == studentId)
                .map( registration -> courses.get(registration.getCourseId()))
                .toList();
    }

    @Override
    public void unregisterStudentFromCourse(long studentId, String courseId) throws NotFoundException {
        if (!students.containsKey(studentId)) {
            throw new NotFoundException("Student not found");
        }
        if (!courses.containsKey(courseId)) {
            throw new NotFoundException("Course not found");
        }

        boolean removed = registrations.removeIf(registration ->
                registration.getStudentId() == studentId && registration.getCourseId().equals(courseId)
        );

        if (!removed) {
            throw new NotFoundException("Registration not found for student " + studentId + " in course " + courseId);
        }
    }

    private boolean isStudentIdExist(long studentId) {
        return students.containsKey(studentId);
    }

    private boolean isCourseIdExist(String courseId) {
        return courses.containsKey(courseId);
    }
}