package org.school.console.app.configurationTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDatabaseInitialization {
    public static void createTables() {
        try (Connection connection = TestDatabase.getConnection()) {

            String createTable =
                    "create table if not exists testTable" +
                            "( testTable_id   serial primary key," +
                            "testTable_name varchar(50) not null); ";

            String insertData =
                    "insert into testTable" +
                            "(testTable_name) VALUES ('testTable_1');";

            Statement currentStatement = connection.createStatement();
            currentStatement.addBatch(createTable);
            currentStatement.addBatch(insertData);
            currentStatement.executeBatch();
        } catch (
                SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}
