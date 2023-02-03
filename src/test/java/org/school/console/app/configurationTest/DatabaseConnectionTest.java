package org.school.console.app.configurationTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.school.console.app.configuration.DatabaseConnection;
import org.school.console.app.configurationForConnectionTest.TestDatabaseInitialization;
import org.school.console.app.configurationForConnectionTest.TestTable;


import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DatabaseConnectionTest {
    private final static TestDatabaseInitialization dbInit = new TestDatabaseInitialization();

    @BeforeAll
    static void setup() {
        dbInit.createTestTable();
    }

    @AfterAll
    static void clear() {
        dbInit.dropTestTable();
    }

    @Test
    void get_testConnection_whenThereIsValue() {
        int testTable_id = 1;
        TestTable expectedTestTable = new TestTable(testTable_id, "testTable_1");
        TestTable actualTestTable = new TestTable();

        String SQL_SAVE = "select * from testTable where testTable_id=?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE)) {

            preparedStatement.setInt(1, testTable_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    actualTestTable.setTestTable_id(resultSet.getInt("testTable_id"));
                    actualTestTable.setTestTable_name(resultSet.getString("testTable_name"));
                }
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        assertEquals(expectedTestTable, actualTestTable);
    }

    @Test
    void save_testConnection_whenThereIsNoValue() {
        boolean expected = true;
        boolean actual = false;

        String SQL_SAVE = "insert into testTable (testTable_name) VALUES ('testTable_7')";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE)) {

            actual = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        assertEquals(expected, actual);
    }

    @Test
    void delete_testConnection_whenThereIsValue() {
        boolean expected = true;
        boolean actual = false;
        String SQL_SAVE = "delete from testTable where testTable_id=?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SAVE)) {

            preparedStatement.setInt(1, 7);

            actual = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        assertEquals(expected, actual);
    }

    @Test
    void update_testConnection_whenThereIsNoValue() {
        boolean expected = true;
        boolean actual = false;
        TestTable newValues = new TestTable(1, "new_name");

        String SQL_UPDATE = "update testTable set testTable_name=? where testTable_id=?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE)) {

            preparedStatement.setString(1, newValues.getTestTable_name());
            preparedStatement.setInt(2, newValues.getTestTable_id());

            actual = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        assertEquals(expected, actual);
    }
}
