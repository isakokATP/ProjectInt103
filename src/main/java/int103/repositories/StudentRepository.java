package int103.repositories;

import int103.entities.Student;
//import int103.exceptions.CustomException;
import int103.exceptions.DatabaseException;
import int103.exceptions.InvalidException;
import int103.exceptions.NotFoundException;

import java.util.List;

public interface StudentRepository {
    void addStudent(long studentId, String firstName, String lastName, String email) throws InvalidException, DatabaseException;
    List<Student> getAllStudents() throws InvalidException, DatabaseException;
    Student getStudentById(long studentId) throws NotFoundException, DatabaseException;
    void deleteStudent(Long studentId) throws NotFoundException, DatabaseException;
}
