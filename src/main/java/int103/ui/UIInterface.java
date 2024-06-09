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
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        deleteStudent();
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
        System.out.println("1. Add Student");
        System.out.println("2. view All Students");
        System.out.println("3. Delete Student");
        System.out.println("4. Add Course");
        System.out.println("5. Edit Course");
        System.out.println("6. Delete Course");
        System.out.println("7. View All Courses");
        System.out.println("8. Register student for course");
        System.out.println("9. View Course For Students");
        System.out.println("10. Exit");
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
        if (id.isEmpty() && name.isEmpty()){
            System.out.println("Your info is not empty.");
            return;
        }
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
    private void editCourse() throws CustomException {
        System.out.print("Enter courseID for viewEdit :");
        String id = scanner.nextLine();
        System.out.print("Enter name for change");
        String name = scanner.next();
        courseService.editCourse(id, name);
    }
    private void addStudent() throws CustomException {
        System.out.print("Enter studentID: ");
        String studentId = scanner.next();
        if (studentId.length() == 11){
            try{
                Long studentCheck = Long.parseLong(studentId);
                System.out.println("The number is valid" + studentCheck);
            } catch (NumberFormatException e){
                System.out.println("the number is not a valid");
            }
        } else{
            System.out.println("The number is too long.");
            return;
        }
        System.out.print("Enter student first name: ");
        String firstname = scanner.next();
        System.out.print("Enter student last name: ");
        String lastName = scanner.next();
        System.out.print("Enter student email: ");
        String email = scanner.next();
        studentService.addStudent(Long.parseLong(studentId), firstname, lastName, email);
        System.out.println("Student added successfully.");
    }
    private void deleteStudent() throws CustomException {
        System.out.print("Enter your studentID for deleted: ");
        Long studentId = scanner.nextLong();

        studentService.deleteStudent(studentId);
        System.out.println("your studentID has been deleted.");
    }
    public void deleteCourse() throws CustomException {
        System.out.print("Enter courseId to deleted: ");
        String courseId = scanner.next();

        courseService.deleteCourse(courseId);
        System.out.println("Your Course has been deleted.");
    }
}