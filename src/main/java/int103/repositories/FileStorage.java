package int103.repositories;

import int103.entities.Course;
import int103.entities.Student;
import int103.exceptions.CustomException;

import java.io.*;
import java.util.*;

public class FileStorage implements StorageStrategy {
    private static final String STUDENTS_FILE = "students.dat";
    private static final String COURSES_FILE = "courses.dat";
    private static final String REGISTRATIONS_FILE = "registrations.dat";

    private Map<Long, Student> students = new HashMap<>();
    private Map<String, String> courses = new HashMap<>();
    private Map<Long, Set<String>> studentCourses = new HashMap<>();
    private Map<String, Set<Long>> courseStudents = new HashMap<>();

    public FileStorage() {
        load();
    }

    @Override
    public void addCourse(String courseId, String courseName) throws CustomException {
        if (courses.containsKey(courseId)) {
            throw new CustomException("Course ID already exists.");
        }
        courses.put(courseId, courseName);
        save();
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
        save();
    }

    @Override
    public List<Student> getAllStudents() throws CustomException {
        return new ArrayList<>(students.values());
    }

    @Override
    public List<Course> getAllCourses() throws CustomException {
        List<Course> courseList = new ArrayList<>();
        for (Map.Entry<String, String> entry : courses.entrySet()) {
            courseList.add(new Course(entry.getKey(), entry.getValue()));
        }
        return courseList;
    }

    @Override
    public List<Course> getCoursesForStudent(long studentId) throws CustomException {
        if (!students.containsKey(studentId)) {
            throw new CustomException("Student not found.");
        }
        return studentCourses.getOrDefault(studentId, Collections.emptySet()).stream()
                .map(courseId -> new Course(courseId, courses.get(courseId)))
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

    private void save() throws CustomException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STUDENTS_FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            throw new CustomException("Error saving students: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COURSES_FILE))) {
            oos.writeObject(courses);
        } catch (IOException e) {
            throw new CustomException("Error saving courses: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(REGISTRATIONS_FILE))) {
            oos.writeObject(studentCourses);
            oos.writeObject(courseStudents);
        } catch (IOException e) {
            throw new CustomException("Error saving registrations: " + e.getMessage());
        }
    }

    private void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STUDENTS_FILE))) {
            students = (Map<Long, Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // File not found or not readable, starting fresh
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COURSES_FILE))) {
            courses = (Map<String, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // File not found or not readable, starting fresh
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(REGISTRATIONS_FILE))) {
            studentCourses = (Map<Long, Set<String>>) ois.readObject();
            courseStudents = (Map<String, Set<Long>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // File not found or not readable, starting fresh
        }
    }
}
