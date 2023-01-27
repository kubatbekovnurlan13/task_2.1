package org.school.console.app.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            DatabaseProperties databaseProperties = new DatabaseProperties();
            String URL = databaseProperties.getURL();
            String USER = databaseProperties.getUSER();
            String PASSWORD = databaseProperties.getPASSWORD();
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
