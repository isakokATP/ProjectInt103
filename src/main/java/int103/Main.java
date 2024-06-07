package int103;
import java.util.Scanner;

import int103.service.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
<<<<<<< HEAD
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
=======
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1.)listCourse");
            System.out.println("2.)listStudent");
            System.out.println("3.)AddCourse");
            System.out.println("4.)EditCourse");
            System.out.println("5.)DeleteCourse");
            System.out.println("6.)studentInfo");
            System.out.println("7.)Register");
            System.out.println("8.)Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();


            switch (choice) {
                case 1: listCourse.list();
                break;
                case 2: listStudent.list();
                break;
                case 3: AddCourse.addCourse();
                break;
                case 4: EditCourse.Edit();
                break;
                case 5: deleteCourse.delete();
                break;
                case 6: studentInfo.info();
                break;
                case 7: Register.add();
                break;
                default:
                    System.out.println("No such choice");
                    break;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
>>>>>>> Chaiy0
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

    public static void main(String[] args) {
        displayMenu();
    }
}

