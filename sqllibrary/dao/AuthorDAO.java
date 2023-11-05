package sqllibrary.dao;

import sqllibrary.entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private Connection connection;

    public AuthorDAO(Connection connection) {
        this.connection = connection;
    }

    // Step 2: Implement the method to add a new author
    public void addAuthor(Author author) {
        String sql = "INSERT INTO tacgia (TenTacGia) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, author.getAuthorName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Step 3: Implement the method to get an author by their unique ID
    public Author getAuthorById(int authorId) {
        String sql = "SELECT * FROM tacgia WHERE MaTacGia = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("MaTacGia");
                String name = resultSet.getString("TenTacGia");
                return new Author(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no author with the given ID is found
    }
}
