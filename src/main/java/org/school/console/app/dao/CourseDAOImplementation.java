package org.school.console.app.dao;

import org.school.console.app.daoInterfaces.CourseDAO;
import org.school.console.app.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImplementation implements CourseDAO {
    private final String URL = "jdbc:postgresql://localhost:5432/schooldb";
    private final String USER = "postgres";
    private final String PASSWORD = "newpostgres";

    @Override
    public void save(Course course) {
        String SQL_SAVE = "insert into courses (course_name, course_description) VALUES (?,?);";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE);

            preparedStatement.setString(1, course.getCourse_name());
            preparedStatement.setString(2, course.getCourse_description());

            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(Course course) {
        boolean updated = false;
        String SQL_SAVE = "update courses set course_name=?, course_description=? where course_id=?;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE);

            preparedStatement.setString(1, course.getCourse_name());
            preparedStatement.setString(2, course.getCourse_description());
            preparedStatement.setInt(3, course.getCourse_id());

            updated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public Course getById(int course_id) {
        Course course = new Course();

        String SQL_SAVE = "select * from courses where course_id=?;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE);
            preparedStatement.setInt(1, course_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                course.setCourse_id(resultSet.getInt("courses_id"));
                course.setCourse_name(resultSet.getString("course_name"));
                course.setCourse_description(resultSet.getString("course_description"));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return course;

    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();

        String SQL_SAVE = "select * from courses;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("course_id");
                String name = resultSet.getString("course_name");
                String desc = resultSet.getString("course_description");

                courses.add(new Course(id, name, desc));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;

    }

    @Override
    public boolean deleteById(int course_id) {
        boolean deleted = false;
        String SQL_SAVE = "delete from courses where course_id=?;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE);
            preparedStatement.setInt(1, course_id);

            deleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }
}
