package int103.ui;

import int103.entities.Course;
import int103.entities.Student;
import int103.exceptions.CustomException;
import int103.repositories.StorageStrategy;

import java.util.Scanner;

public class CommandLineInterface {
    private final StorageStrategy storage;
    private final Scanner scanner;

    public CommandLineInterface(StorageStrategy storage) {
        this.storage = storage;
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
                        viewStudentsForCourse();
                        break;
                    case 7:
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
        System.out.println("6. View Students for Course");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private void viewAllStudents() throws CustomException {
        for (Student student : storage.getAllStudents()) {
            System.out.println(student);
        }
    }

    private void addCourse() throws CustomException {
        System.out.print("Enter course ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        storage.addCourse(id, name);
        System.out.println("Course added successfully.");
    }

    private void viewAllCourses() throws CustomException {
        for (Course course : storage.getAllCourses()) {
            System.out.println(course);
        }
    }

    private void registerStudentForCourse() throws CustomException {
        System.out.print("Enter student ID: ");
        long studentId = scanner.nextLong();
        scanner.nextLine(); // consume newline
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        storage.registerStudentForCourse(studentId, courseId);
        System.out.println("Student registered for course successfully.");
    }

    private void viewCoursesForStudent() throws CustomException {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        for (Course course : storage.getCoursesForStudent(studentId)) {
            System.out.println(course);
        }
    }

    private void viewStudentsForCourse() throws CustomException {
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        for (Student student : storage.getStudentsForCourse(courseId)) {
            System.out.println(student);
        }
    }
}

