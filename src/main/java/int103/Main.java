package int103;
import java.util.Scanner;

public class Main {
    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Choose your options: ");
            System.out.println("1. In memory");
            System.out.println("2. jar file");
            System.out.println("3. Database");
            System.out.print("Enter your option: ");
            int intValue = scanner.nextInt();
            System.out.println("You selected option: " + intValue);

            if (intValue == 1) {
                System.out.println(" ");
                System.out.print("In memory ");
                running =false;
                menuInterface(intValue);
            } else if (intValue == 2) {
                System.out.println(" ");
                System.out.print("jar file ");
                running =false;
                menuInterface(intValue);
            } else if (intValue == 3) {
                System.out.println(" ");
                System.out.print("Database ");
                running =false;
                menuInterface(intValue);
            } else {
                System.out.println(" ");
                System.out.println("Invalid option.");
            }
            System.out.println("You selected option: " + intValue);

        }

        scanner.close();
    }

    public static void menuInterface(int option) {
        System.out.println("Option " + option);
        System.out.println("Choose your Menu: ");
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while(run) {
            System.out.println("1. All Students");
            System.out.println("2. Students Info");
            System.out.println("3. All Course");
            System.out.println("4. Add Course");
            System.out.println("5. Delete Course");
            System.out.println("6. Register");
            System.out.println("7. Exit");
            System.out.print("Enter your option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("All Students");
                    break;
                case 2:
                    System.out.println("Students Info");
                    break;
                case 3:
                    System.out.println("All Course");
                    break;
                case 4:
                    System.out.println("Add Course");
                    break;
                case 5:
                    System.out.println("Delete Course");
                    break;
                case 6:
                    System.out.println("Register");
                    break;
                case 7:
                    System.out.println("See you again BYE!!");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        scanner.close();

    }

    public static void database(){

    }

    public static void main(String[] args) {
        displayMenu();
    }
}

