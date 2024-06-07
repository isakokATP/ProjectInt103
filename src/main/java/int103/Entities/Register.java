package int103.entities;

public class Register {
    private int registrationId;
<<<<<<< HEAD
    private Long studentId;
    private String courseId;

    public Register(int registrationId, Long studentId, String courseId) {
=======
    private String studentId;
    private String courseId;

    public Register(int registrationId, String studentId, String courseId) {
>>>>>>> Chaiy0
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

<<<<<<< HEAD
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
=======
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
>>>>>>> Chaiy0
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
<<<<<<< HEAD
        return "Register{" +
                "registration_id=" + registrationId +
                ", student_id=" + studentId +
                ", course_id='" + courseId + '\'' +
                '}';
=======
        return "Registration [registrationId=" + registrationId + ", studentId=" + studentId + ", courseId=" + courseId + "]";
>>>>>>> Chaiy0
    }
}

