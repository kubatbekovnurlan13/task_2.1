package org.school.console.app.configurationForConnectionTest;

import org.school.console.app.configuration.DatabaseConnection;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestDatabaseInitialization {
    private final Path sqlSchemaPath = TestDatabasePath.getSqlSchemaPath();
    private final Path sqlTableCreationPath = TestDatabasePath.getSqlDataPath();
    private final Path sqlDropSchemaPath = TestDatabasePath.getSqlDropPath();

    public void createTestTable() {
        executeSqlFile(sqlSchemaPath);
        executeSqlFile(sqlTableCreationPath);
    }

    public void dropTestTable() {
        executeSqlFile(sqlDropSchemaPath);
    }

    private void executeSqlFile(Path path) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String delimiter = ";";

            Scanner scanner = getScanner(delimiter, path);

            Statement currentStatement;
            while (scanner.hasNext()) {
                String rawStatement = scanner.next() + delimiter;
                try {
                    currentStatement = conn.createStatement();
                    currentStatement.execute(rawStatement);
                } catch (SQLException e) {
                    System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                }
            }
            scanner.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    private Scanner getScanner(String delimiter, Path path) {
        Scanner scanner;
        try {
            scanner = new Scanner(path).useDelimiter(delimiter);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return scanner;
    }
}
