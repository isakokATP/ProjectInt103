package int103;

import int103.connector.DatabaseConnector;
import int103.entities.Login;
import int103.repositories.database.DatabaseStorage;
import int103.repositories.file.FileStorage;
import int103.repositories.StorageStrategy;
import int103.repositories.memory.InMemoryStorage;
import int103.services.CourseService;
import int103.services.RegistrationService;
import int103.services.StudentService;
import int103.ui.UIInterface;

import java.io.Console;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();
        System.out.println("---------- Log in ----------");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        String password;
        System.out.print("Enter your password: ");
        password = scanner.nextLine();

        if (!login.checkLogin(username, password)) {
            System.out.println("username or password doesn't correct \n");
            return;
        }
        System.out.println("Select storage type (memory/file/database): ");
        String storageType = scanner.nextLine().trim().toLowerCase();

        StorageStrategy storage;
        DatabaseConnector connector = new DatabaseConnector();
        Connection connection = null;

        try {
            switch (storageType) {
                case "database":
                    connection = connector.connect();
                    storage = new DatabaseStorage(connection);
                    break;
                case "file":
                    storage = new FileStorage();
                    break;
                case "memory":
                    storage = new InMemoryStorage();
                    break;
                default:
                    System.out.println("Invalid storage type. Defaulting to memory.");
                    storage = new InMemoryStorage();
                    break;
            }

            StudentService studentService = new StudentService(storage);
            CourseService courseService = new CourseService(storage);
            RegistrationService registrationService = new RegistrationService(storage);

            UIInterface cli = new UIInterface(studentService, courseService, registrationService);
            cli.run();

        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connector.disconnect();
                } catch (SQLException e) {
                    System.out.println("Error closing database connection: " + e.getMessage());
                }
            }
        }
    }
}