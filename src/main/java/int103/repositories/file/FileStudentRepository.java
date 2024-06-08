package int103.repositories.file;

import int103.entities.Student;
import int103.exceptions.CustomException;
import int103.repositories.StudentRepository;

import java.io.*;
import java.util.*;

public class FileStudentRepository implements StudentRepository {
    private static final String STUDENTS_FILE = "students.dat";
    private Map<Long, Student> students = new HashMap<>();

    public FileStudentRepository() {
        load();
    }
    @Override
    public List<Student> getAllStudents() throws CustomException {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student getStudentById(long studentId) throws CustomException {
        return students.get(studentId);
    }


    private void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STUDENTS_FILE))) {
            students = (Map<Long, Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new CustomException("Error loading courses from file", e);
        }
    }

    private void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STUDENTS_FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            throw new CustomException("Error saving courses to file", e);
        }
    }

}
