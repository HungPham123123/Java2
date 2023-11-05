package sqllibrary.controller;

import sqllibrary.dao.StudentDAO;
import sqllibrary.entity.Student;

import java.sql.Connection;

public class StudentController {
    private StudentDAO studentDAO;

    public StudentController(Connection connection) {
        studentDAO = new StudentDAO(connection);
    }

    // Step 2: Implement the method to add a new student
    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }

    // Step 3: Implement the method to get a student by their unique ID
    public Student getStudentById(int studentId) {
        return studentDAO.getStudentById(studentId);
    }
}
