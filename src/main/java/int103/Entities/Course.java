package int103.entities;

<<<<<<< HEAD
import java.io.Serializable;

public class Course implements Serializable {
    private final String id;
    private final String name;

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
=======
public class Course {
    private String courseId;
    private String courseName;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
>>>>>>> Chaiy0
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
=======
        return "Course [courseId=" + courseId + ", courseName=" + courseName + "]";
>>>>>>> Chaiy0
    }
}

