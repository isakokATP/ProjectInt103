package int103.services;

import int103.entities.Course;
import int103.exceptions.CustomException;
import int103.repositories.CourseRepository;

import java.util.List;

public class CourseService {
    private final CourseRepository courseRepo;

    public CourseService(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    public void addCourse(String courseId, String courseName) throws CustomException {
        courseRepo.addCourse(courseId, courseName);
    }
    public void editCourse(String courseId, String courseName) throws CustomException{
        courseRepo.editCourse(courseId, courseName);
    }
    public void deleteCourse(String courseId) throws CustomException {
        courseRepo.deleteCourse(courseId);
    }
    public List<Course> getAllCourses() throws CustomException {
        return courseRepo.getAllCourses();
    }
}