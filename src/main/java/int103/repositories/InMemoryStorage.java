package int103.repositories;

import int103.entities.Course;
import int103.entities.Student;
import int103.exceptions.CustomException;

import java.util.*;

public class InMemoryStorage implements StorageStrategy {
    private final Map<Long, Student> students = new HashMap<>();
    private final Map<String, Course> courses = new HashMap<>();
    private final Map<Long, Set<String>> studentCourses = new HashMap<>();
    private final Map<String, Set<Long>> courseStudents = new HashMap<>();

    @Override
    public void addCourse(String courseId, String courseName) throws CustomException {
        if (courses.containsKey(courseId)) {
            throw new CustomException("Course ID already exists.");
        }
        courses.put(courseId, new Course(courseId, courseName));
    }

    @Override
    public void registerStudentForCourse(long studentId, String courseId) throws CustomException {
        if (!students.containsKey(studentId)) {
            throw new CustomException("Student not found.");
        }
        if (!courses.containsKey(courseId)) {
            throw new CustomException("Course not found.");
        }
        studentCourses.computeIfAbsent(studentId, k -> new HashSet<>()).add(courseId);
        courseStudents.computeIfAbsent(courseId, k -> new HashSet<>()).add(studentId);
    }

    @Override
    public List<Student> getAllStudents() throws CustomException {
        return new ArrayList<>(students.values());
    }

    @Override
    public List<Course> getAllCourses() throws CustomException {
        return new ArrayList<>(courses.values());
    }

    @Override
    public List<Course> getCoursesForStudent(long studentId) throws CustomException {
        if (!students.containsKey(studentId)) {
            throw new CustomException("Student not found.");
        }
        return studentCourses.getOrDefault(studentId, Collections.emptySet()).stream()
                .map(courses::get)
                .toList();
    }

    @Override
    public List<Student> getStudentsForCourse(String courseId) throws CustomException {
        if (!courses.containsKey(courseId)) {
            throw new CustomException("Course not found.");
        }
        return courseStudents.getOrDefault(courseId, Collections.emptySet()).stream()
                .map(students::get)
                .toList();
    }
}

