package int103.repositories.memory;

import int103.entities.Student;
import int103.exceptions.CustomException;
import int103.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryStudentRepository implements StudentRepository {
    private final Map<Long, Student> students = new HashMap<>();
    @Override
    public List<Student> getAllStudents() throws CustomException {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student getStudentById(long studentId) throws CustomException {
        return students.get(studentId);
    }
}
