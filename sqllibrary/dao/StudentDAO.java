package sqllibrary.dao;

import sqllibrary.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    // Step 2: Implement the method to add a new student
    public void addStudent(Student student) {
        String sql = "INSERT INTO sinhvien (TenSinhVien) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Step 3: Implement the method to get a student by their unique ID
    public Student getStudentById(int studentId) {
        String sql = "SELECT * FROM sinhvien WHERE MaSinhVien = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("MaSinhVien");
                String name = resultSet.getString("TenSinhVien");
                return new Student(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no student with the given ID is found
    }
}
