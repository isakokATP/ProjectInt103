package int103.repositories.memory;

import int103.entities.Course;
import int103.exceptions.CustomException;
import int103.repositories.CourseRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCourseRepository implements CourseRepository {
    private final Map<String, Course> courses = new HashMap<>();

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

}
