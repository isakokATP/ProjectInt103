package int103.repositories.file;

import int103.entities.Course;
import int103.entities.Register;
import int103.entities.Student;
import int103.exceptions.InvalidException;
import int103.exceptions.NotFoundException;
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


    private boolean isStudentIdExist(long studentId) {
        return students.containsKey(studentId);
    }
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
            throw new InvalidException("The student ID already exists in the file");
        }
        students.put(studentId, new Student(studentId, firstName, lastName, email));
        save();
    }

    @Override
    public void deleteStudent(Long studentId) throws NotFoundException {
        if (!students.containsKey(studentId)){
            throw new NotFoundException("Students not found");
        }
        students.remove(studentId);
        registrations.removeIf(registration -> registration.getStudentId().equals(studentId));
        save();
    }

    @Override
    public List<Student> getAllStudents() throws NotFoundException {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student getStudentById(long studentId) throws NotFoundException {
        return students.get(studentId);
    }

    private boolean isCourseIdExist(String courseId) {
        return courses.containsKey(courseId);
    }

    @Override
    public void addCourse(String courseId, String courseName) throws InvalidException {
        if (courseId.isEmpty() && courseName == null || courseName.isEmpty()){
            throw new InvalidException("Course is not null or empty.");
        }
        if (isCourseIdExist(courseId)) {
            throw new InvalidException("The course ID already exists in the file");
        }
        courses.put(courseId, new Course(courseId, courseName));
        save();
    }

    @Override
    public List<Course> getAllCourses() throws NotFoundException {
        return new ArrayList<>(courses.values());
    }

    @Override
    public Course getCourseById(String courseId) throws NotFoundException {
        return courses.get(courseId);
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
        save();
    }

    @Override
    public void deleteCourse(String courseId) throws NotFoundException {
        if (!courses.containsKey(courseId)) {
            throw new NotFoundException("Course not found");
        }
        courses.remove(courseId);
        registrations.removeIf(registration -> registration.getCourseId().equals(courseId));
        save();
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
        save();
    }

    @Override
    public List<Course> getCoursesForStudent(long studentId) throws NotFoundException {
        if (!students.containsKey(studentId)) {
            throw new NotFoundException("Student not found.");
        }
        return registrations.stream()
                .filter(registration -> registration.getStudentId() == studentId)
                .map(registration -> courses.get(registration.getCourseId()))
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
                registration.getStudentId() == studentId && registration.getCourseId().equals(courseId));
        if (!removed) {
            throw new NotFoundException("Registration not found for the given student and course");
        }
        save();
    }

    private void save() throws NotFoundException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STUDENTS_FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            throw new NotFoundException("Error saving students: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COURSES_FILE))) {
            oos.writeObject(courses);
        } catch (IOException e) {
            throw new NotFoundException("Error saving courses: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(REGISTRATIONS_FILE))) {
            oos.writeObject(registrations);
        } catch (IOException e) {
            throw new NotFoundException("Error saving registrations: " + e.getMessage());
        }
    }

    private void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STUDENTS_FILE))) {
            students = (Map<Long, Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            students = new HashMap<>();
            save();
        } catch (IOException | ClassNotFoundException e) {
            throw new NotFoundException("Error loading students: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COURSES_FILE))) {
            courses = (Map<String, Course>) ois.readObject();
        } catch (FileNotFoundException e) {
            courses = new HashMap<>();
            save();
        } catch (IOException | ClassNotFoundException e) {
            throw new NotFoundException("Error loading courses: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(REGISTRATIONS_FILE))) {
            registrations = (List<Register>) ois.readObject();
        } catch (FileNotFoundException e) {
            registrations = new ArrayList<>();
            save();
        } catch (IOException | ClassNotFoundException e) {
            throw new NotFoundException("Error loading registrations: " + e.getMessage());
        }
    }

}
