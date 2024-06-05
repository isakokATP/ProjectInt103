package int103.Entities;

public class Register {
    private int registration_id;
    private Long student_id;
    private String course_id;

    public Register(int registration_id, Long student_id, String course_id) {
        this.registration_id = registration_id;
        this.student_id = student_id;
        this.course_id = course_id;
    }

    public int getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(int registration_id) {
        this.registration_id = registration_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "Register{" +
                "registration_id=" + registration_id +
                ", student_id=" + student_id +
                ", course_id='" + course_id + '\'' +
                '}';
    }
}
