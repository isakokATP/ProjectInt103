package int103.ui;

import int103.entities.Course;
import int103.entities.Student;
import int103.exceptions.DatabaseException;
import int103.exceptions.InvalidException;
import int103.exceptions.NotFoundException;
import int103.services.CourseService;
import int103.services.RegistrationService;
import int103.services.StudentService;

import java.util.Scanner;

public class UIInterface {
    private final StudentService studentService;
    private final CourseService courseService;
    private final RegistrationService registrationService;
    private final Scanner scanner;

    public UIInterface(StudentService studentService, CourseService courseService, RegistrationService registrationService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.registrationService = registrationService;
        this.scanner = new Scanner(System.in);
    }

    public void run() throws InvalidException, DatabaseException {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    viewAllStudents();
                    break;
                case 4:
                    addCourse();
                    break;
                case 5:
                    editCourse();
                    break;
                case 6:
                    deleteCourse();
                    break;
                case 7:
                    viewAllCourses();
                    break;
                case 8:
                    registerStudentForCourse();
                    break;
                case 9:
                    viewCoursesForStudent();
                    break;
                case 10:
                    unregisterStudentFromCourse();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("1. Add Student");
        System.out.println("2. Delete Student");
        System.out.println("3. View All Students");
        System.out.println("4. Add Course");
        System.out.println("5. Edit Course");
        System.out.println("6. Delete Course");
        System.out.println("7. View All Courses");
        System.out.println("8. Register Student For Course");
        System.out.println("9. View Course For Students");
        System.out.println("10. Unregister Course");
        System.out.println("11. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addStudent() throws InvalidException {
        try {
            System.out.print("Enter studentID: ");
            String studentId = scanner.nextLine();

            System.out.print("Enter student first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter student last name: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter student email: ");
            String email = scanner.nextLine();

            studentService.addStudent(Long.parseLong(studentId), firstName, lastName, email);
            System.out.println("Student added successfully.");
        }catch (Exception e) {
            throw new InvalidException("Error add student: " + e.getMessage());
        }
    }

    private void deleteStudent() throws NotFoundException, DatabaseException {
            System.out.print("Enter your studentID for deleted: ");
            Long studentId = scanner.nextLong();
            studentService.deleteStudent(studentId);
            System.out.println("your studentID has been deleted.");
    }

    private void viewAllStudents() throws NotFoundException, DatabaseException {
        for (Student student : studentService.getAllStudents()) {
            System.out.println(student.getId() + ", " + student.getFirstName() + " " + student.getLastName() + ", " + student.getEmail());
        }
    }

    private void addCourse() throws DatabaseException, InvalidException {
        System.out.print("Enter course ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        courseService.addCourse(id, name);
        System.out.println("Course added successfully.");
    }

    private void editCourse() throws NotFoundException, DatabaseException {
        System.out.print("Enter courseID for viewEdit :");
        String id = scanner.nextLine();
        System.out.print("Enter name for change");
        String name = scanner.next();
        courseService.editCourse(id, name);
        System.out.println("Course edited successfully.");
    }

    public void deleteCourse() throws NotFoundException, DatabaseException {
        System.out.print("Enter courseId to deleted: ");
        String courseId = scanner.next();

        courseService.deleteCourse(courseId);
        System.out.println("Your Course has been deleted.");
    }

    private void viewAllCourses() throws DatabaseException {
        for (Course course : courseService.getAllCourses()) {
            System.out.println(course.getId() + " : " + course.getName());
        }
    }

    private void registerStudentForCourse() throws NotFoundException, DatabaseException {
        System.out.print("Enter student ID: ");
        long studentId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        registrationService.registerStudentForCourse(studentId, courseId);
        System.out.println("Student registered for course successfully.");
    }

    private void viewCoursesForStudent() throws NotFoundException, DatabaseException {
        System.out.print("Enter student ID: ");
        long studentId = scanner.nextLong();
        scanner.nextLine();
        for (Course course : registrationService.getCoursesForStudent(studentId)) {
            System.out.println(course.getId() + " : " + course.getName());
        }
    }

    private void unregisterStudentFromCourse() throws NotFoundException, DatabaseException {
        System.out.print("Enter studentID to deleted: ");
        String studentId = scanner.next();
        long studentCheck = Long.parseLong(studentId);
        System.out.print("Enter courseID to deleted: ");
        String courseId = scanner.next();

        registrationService.unregisterStudentFromCourse(studentCheck, courseId);
        System.out.println("Your Course has been deleted.");
    }
}