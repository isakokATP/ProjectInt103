package int103.services;

import int103.entities.Student;
//import int103.exceptions.CustomException;
import int103.exceptions.DatabaseException;
import int103.exceptions.InvalidException;
import int103.exceptions.NotFoundException;
import int103.repositories.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository studentRepo;

    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public void addStudent(long studentId, String firstName, String lastName, String email) throws NotFoundException, DatabaseException, InvalidException {
        studentRepo.addStudent(studentId, firstName, lastName, email);
    }
    public void deleteStudent(Long studentId) throws NotFoundException, DatabaseException{
        studentRepo.deleteStudent(studentId);
    }
    public List<Student> getAllStudents() throws NotFoundException, DatabaseException, InvalidException {
        return studentRepo.getAllStudents();
    }
}
