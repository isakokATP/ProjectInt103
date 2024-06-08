package int103.repositories.file;

import int103.entities.Course;
import int103.entities.Register;
import int103.entities.Student;
import int103.exceptions.CustomException;
import int103.repositories.StorageStrategy;

import java.io.*;
import java.util.*;

public class FileStorage implements StorageStrategy {
    private static final String STUDENTS_FILE = "students.dat";
    private static final String COURSES_FILE = "courses.dat";
    private static final String REGISTRATIONS_FILE = "registrations.dat";

    private Map<Long, Student> students = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();
    private List<Register> registrations = new ArrayList<>();

    public FileStorage() {
        load();
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
        save();
    }

    @Override
    public void registerStudentForCourse(long studentId, String courseId) throws CustomException {
        registrations.add(new Register(registrations.size() + 1, studentId, courseId));
        save();
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
            oos.writeObject(registrations);
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
            courses = (Map<String, Course>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // File not found or not readable, starting fresh
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(REGISTRATIONS_FILE))) {
            registrations = (List<Register>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // File not found or not readable, starting fresh
        }
    }
}
