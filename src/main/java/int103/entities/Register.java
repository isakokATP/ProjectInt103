package int103.entities;

public class Register {
    private int registrationId;
    private Long studentId;
    private String courseId;

    public Register(int registrationId, Long studentId, String courseId) {
        this.registrationId = registrationId;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Register{" +
                "registration_id=" + registrationId +
                ", student_id=" + studentId +
                ", course_id='" + courseId + '\'' +
                '}';
    }
}
