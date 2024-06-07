//package int103.services;
//
//import int103.entities.Course;
//import int103.entities.Student;
//import int103.exceptions.RegistrationException;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class RegistrationService {
//    private RegistrationRepository registrationRepository;
//
//    public RegistrationService(RegistrationRepository registrationRepository) {
//        this.registrationRepository = registrationRepository;
//    }
//
//    public void registerStudentForCourse(Students student, Course course) throws RegistrationException {
//        try {
//            registrationRepository.registerStudentForCourse(student.getStudentId(), course.getCourseId());
//        } catch (SQLException e) {
//            throw new RegistrationException("Error registering student for course: " + e.getMessage());
//        }
//    }
//
//    public List<Course> getCoursesForStudent(Students student) throws RegistrationException {
//        try {
//            return registrationRepository.getCoursesForStudent(student.getStudentId());
//        } catch (SQLException e) {
//            throw new RegistrationException("Error retrieving courses for student: " + e.getMessage());
//        }
//    }
//
//    public List<Students> getStudentsForCourse(Course course) throws RegistrationException {
//        try {
//            return registrationRepository.getStudentsForCourse(course.getCourseId());
//        } catch (SQLException e) {
//            throw new RegistrationException("Error retrieving students for course: " + e.getMessage());
//        }
//    }
//}
