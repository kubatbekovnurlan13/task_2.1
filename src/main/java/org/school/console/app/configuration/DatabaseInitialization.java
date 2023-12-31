package org.school.console.app.configuration;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseInitialization {
    private final Path sqlSchemaPath = DatabasePath.getSqlSchemaPath();
    private final Path sqlTableCreationPath = DatabasePath.getSqlDataPath();

    public void init() {
        ArrayList<String> tableNames = DatabaseTablesProperties.getTables();

        if (checkIfTablesExists(tableNames)) {
            dropTables(tableNames);
        }
        createTables(sqlSchemaPath);
        createTables(sqlTableCreationPath);
    }

    private void dropTables(ArrayList<String> tableNames) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement statement = conn.createStatement()) {

            for (String name : tableNames) {
                statement.addBatch("drop table " + name + ";");
            }
            statement.executeBatch();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    private boolean checkIfTablesExists(ArrayList<String> tableNames) {
        boolean tableExits = false;

        try (Connection conn = DatabaseConnection.getConnection()) {
            DatabaseMetaData databaseMetaData = conn.getMetaData();

            ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});

            while (resultSet.next()) {
                String name = resultSet.getString("TABLE_NAME");
                if (tableNames.contains(name)) {
                    tableExits = true;
                }
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        return tableExits;
    }

    private void createTables(Path path) {
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
            throw new RuntimeException(e);
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
