package org.school.console.app.dao;

import org.school.console.app.daoInterfaces.CourseDAO;
import org.school.console.app.model.Course;
import org.school.console.app.configuration.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImplementation implements CourseDAO {

    @Override
    public void save(Course course) {
        String SQL_SAVE = "insert into courses (course_name, course_description) VALUES (?,?);";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE)) {

            preparedStatement.setString(1, course.getCourse_name());
            preparedStatement.setString(2, course.getCourse_description());

            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    @Override
    public boolean update(Course course) {
        String SQL_SAVE = "update courses set course_name=?, course_description=? where course_id=?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE)) {

            preparedStatement.setString(1, course.getCourse_name());
            preparedStatement.setString(2, course.getCourse_description());
            preparedStatement.setInt(3, course.getCourse_id());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return false;
    }

    @Override
    public Course getById(int course_id) {
        Course course = new Course();

        String SQL_SAVE = "select * from courses where course_id=?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE)) {

            preparedStatement.setInt(1, course_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    course.setCourse_id(resultSet.getInt("courses_id"));
                    course.setCourse_name(resultSet.getString("course_name"));
                    course.setCourse_description(resultSet.getString("course_description"));
                }
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return course;

    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();

        String SQL_SAVE = "select * from courses;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("course_id");
                String name = resultSet.getString("course_name");
                String desc = resultSet.getString("course_description");

                courses.add(new Course(id, name, desc));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return courses;

    }

    @Override
    public boolean deleteById(int course_id) {
        String SQL_SAVE = "delete from courses where course_id=?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE)) {

            preparedStatement.setInt(1, course_id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return false;
    }
}
