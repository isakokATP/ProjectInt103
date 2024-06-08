package int103.services;

import int103.entities.Student;
import int103.exceptions.CustomException;
import int103.repositories.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository studentRepo;

    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getAllStudents() throws CustomException {
        return studentRepo.getAllStudents();
    }

}
