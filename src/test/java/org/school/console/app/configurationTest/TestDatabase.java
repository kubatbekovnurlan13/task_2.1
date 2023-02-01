package org.school.console.app.configurationTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabase {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/testdb",
                    "postgres",
                    "newpostgres");
        }
        return connection;
    }
}
