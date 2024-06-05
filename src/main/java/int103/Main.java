package int103;

import int103.connector.DatabaseConnector;
import int103.exceptions.CustomException;
import int103.repositories.DatabaseStorage;
import int103.repositories.FileStorage;
import int103.repositories.InMemoryStorage;
import int103.repositories.StorageStrategy;
import int103.ui.CommandLineInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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

            CommandLineInterface cli = new CommandLineInterface(storage);
            cli.run();

        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        } catch (CustomException e) {
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