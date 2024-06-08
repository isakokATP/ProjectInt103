package int103.ui;

import int103.entities.Course;
import int103.entities.Student;
import int103.exceptions.CustomException;
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

    public void run() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            try {
                switch (choice) {
                    case 1:
                        viewAllStudents();
                        break;
                    case 2:
                        addCourse();
                        break;
                    case 3:
                        viewAllCourses();
                        break;
                    case 4:
                        registerStudentForCourse();
                        break;
                    case 5:
                        viewCoursesForStudent();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (CustomException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("1. View All Students");
        System.out.println("2. Add Course");
        System.out.println("3. View All Courses");
        System.out.println("4. Register Student for Course");
        System.out.println("5. View Courses for Student");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private void viewAllStudents() throws CustomException {
        for (Student student : studentService.getAllStudents()) {
            System.out.println(student.getId() + ", " + student.getFirstName() + " " + student.getLastName() + ", " + student.getEmail());
        }
    }

    private void addCourse() throws CustomException {
        System.out.print("Enter course ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        courseService.addCourse(id, name);
        System.out.println("Course added successfully.");
    }

    private void viewAllCourses() throws CustomException {
        for (Course course : courseService.getAllCourses()) {
            System.out.println(course.getId() + " : " + course.getName());
        }
    }

    private void registerStudentForCourse() throws CustomException {
        System.out.print("Enter student ID: ");
        long studentId = scanner.nextLong();
        scanner.nextLine(); // consume newline
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        registrationService.registerStudentForCourse(studentId, courseId);
        System.out.println("Student registered for course successfully.");
    }

    private void viewCoursesForStudent() throws CustomException {
        System.out.print("Enter student ID: ");
        long studentId = scanner.nextLong();
        scanner.nextLine(); // consume newline
        for (Course course : registrationService.getCoursesForStudent(studentId)) {
            System.out.println(course);
        }
    }
}