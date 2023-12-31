package org.school.console.app.dao;

import org.school.console.app.daoInterfaces.StudentDAO;
import org.school.console.app.model.Student;
import org.school.console.app.configuration.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImplementation implements StudentDAO {
    @Override
    public void save(Student student) {

        String SQL_SAVE = "insert into students (first_name, last_name, group_id) VALUES (?,?,?);";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE)) {

            preparedStatement.setString(1, student.getFirst_name());
            preparedStatement.setString(2, student.getLast_name());
            preparedStatement.setInt(3, student.getGroup_id());

            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    @Override
    public boolean update(Student student) {
        String SQL_SAVE = "update students set first_name=?, last_name=?, group_id=? where student_id=?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE)) {

            preparedStatement.setString(1, student.getFirst_name());
            preparedStatement.setString(2, student.getLast_name());
            preparedStatement.setInt(3, student.getGroup_id());
            preparedStatement.setInt(4, student.getStudent_id());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return false;
    }

    @Override
    public Student getById(int student_id) {
        Student student = new Student();

        String SQL_SAVE = "select * from students where student_id=?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE)) {

            preparedStatement.setInt(1, student_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    student.setStudent_id(resultSet.getInt("student_id"));
                    student.setFirst_name(resultSet.getString("first_name"));
                    student.setLast_name(resultSet.getString("last_name"));
                    student.setGroup_id(resultSet.getInt("group_id"));
                }
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return student;
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();

        String SQL_SAVE = "select * from students;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("student_id");
                String name = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int groupId = resultSet.getInt("group_id");

                students.add(new Student(id, name, lastName, groupId));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return students;
    }

    @Override
    public boolean deleteById(int student_id) {
        String SQL_SAVE = "delete from students where student_id=?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE)) {

            preparedStatement.setInt(1, student_id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return false;
    }
}
