package org.school.console.app.dao;

import org.school.console.app.daoInterfaces.GroupDAO;
import org.school.console.app.model.Group;
import org.school.console.app.configuration.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDAOImplementation implements GroupDAO {
    @Override
    public void save(Group group) {

        String SQL_SAVE = "insert into groups (group_name) VALUES (?);";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE)) {

            preparedStatement.setString(1, group.getGroup_name());

            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    @Override
    public boolean update(Group group) {
        String SQL_SAVE = "update groups set group_name=? where group_id=?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE)) {

            preparedStatement.setString(1, group.getGroup_name());
            preparedStatement.setInt(2, group.getGroup_id());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return false;
    }

    @Override
    public Group getById(int group_id) {
        Group group = new Group();

        String SQL_SAVE = "select * from groups where group_id=?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE)) {

            preparedStatement.setInt(1, group_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    group.setGroup_id(resultSet.getInt("group_id"));
                    group.setGroup_name(resultSet.getString("group_name"));
                }
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return group;

    }

    @Override
    public List<Group> getAll() {
        List<Group> groups = new ArrayList<>();

        String SQL_SAVE = "select * from groups;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("group_id");
                String name = resultSet.getString("group_name");

                groups.add(new Group(id, name));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return groups;
    }

    @Override
    public boolean deleteById(int group_id) {
        String SQL_SAVE = "delete from groups where group_id=?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE)) {

            preparedStatement.setInt(1, group_id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return false;
    }
}
