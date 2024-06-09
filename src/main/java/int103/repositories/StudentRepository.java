package int103.repositories;

import int103.entities.Student;
import int103.exceptions.CustomException;

import java.util.List;

public interface StudentRepository {
    void addStudent(long studentId, String firstName, String lastName, String email) throws CustomException;
    List<Student> getAllStudents() throws CustomException;
    Student getStudentById(long studentId) throws CustomException;
    void deleteStudent(Long studentId) throws CustomException;
}
