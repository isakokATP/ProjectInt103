package int103;

import int103.service.*;

public class Main {
    public static void main(String[] args) {
        try {
            AddCourse.addCourse();
            deleteCourse.delete();
            listCourse.list();
            listStudent.list();
            Register.add();
            studentInfo.info();
        } catch (Exception e){
            System.out.println("Something went wrong" + e.getMessage());
        }
    }
}