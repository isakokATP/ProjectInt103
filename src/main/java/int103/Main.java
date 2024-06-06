package int103;

import int103.service.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
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
    }
}