package int103.entities;

public class Register {
    private int registrationId;
    private String studentId;
    private String courseId;

    public Register(int registrationId, String studentId, String courseId) {
        this.registrationId = registrationId;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
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
        return "Registration [registrationId=" + registrationId + ", studentId=" + studentId + ", courseId=" + courseId + "]";
    }
}

