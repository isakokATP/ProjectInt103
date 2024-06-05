package int103;

import int103.service.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try{
//            AddCourse.addCourse();
//            deleteCourse.delete();
//            listCourse.list();
//            listStudent.list();
            Register.add();
//            studentInfo.info();
        }catch (Exception e){
            e.getMessage();
        }
    }
}