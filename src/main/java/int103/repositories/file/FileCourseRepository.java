package int103.repositories.file;

import int103.entities.Course;
import int103.exceptions.CustomException;
import int103.repositories.CourseRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileCourseRepository implements CourseRepository {
    private static final String COURSES_FILE = "courses.dat";
    private Map<String, Course> courses = new HashMap<>();

    public FileCourseRepository() {
        load();
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

    private void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COURSES_FILE))) {
            courses = (Map<String, Course>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new CustomException("Error loading courses from file", e);
        }
    }

    private void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COURSES_FILE))) {
            oos.writeObject(courses);
        } catch (IOException e) {
            throw new CustomException("Error saving courses to file", e);
        }
    }

}
