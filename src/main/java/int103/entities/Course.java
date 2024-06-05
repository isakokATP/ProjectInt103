package int103.entities;

public class Course {
    private String courseId;
    private String courseName;

    public Course(String courseId, Course courseName){
        this.courseId = courseId;
        this.courseName = courseName.toString();
    }

    public String getCourse_id() {
        return courseId;
    }

    public void setCourse_id(String courseId) {
        this.courseId = courseId;
    }

    public String getCourse_name() {
        return courseName;
    }

    public void setCourse_name(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
