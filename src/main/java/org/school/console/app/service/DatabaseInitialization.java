package org.school.console.app.service;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseInitialization {
    private final Path sqlSchemaPath = DatabasePath.getSqlSchemaPath();
    private final Path sqlTableCreationPath = DatabasePath.getSqlTableCreationPath();

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
                String sql = "drop table " + name + ";";
                statement.execute(sql);
            }
        }catch (SQLException | RuntimeException e) {
            if (e instanceof SQLException sqlException) {
                System.err.format("SQL State: %s\n%s", sqlException.getSQLState(), sqlException.getMessage());
            } else {
                e.printStackTrace();
            }
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

        } catch (SQLException | RuntimeException e) {
            if (e instanceof SQLException sqlException) {
                System.err.format("SQL State: %s\n%s", sqlException.getSQLState(), sqlException.getMessage());
            } else {
                e.printStackTrace();
            }
        }

        return tableExits;
    }

    private void createTables(Path path) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String delimiter = ";";

            Scanner scanner = getScanner(delimiter, path);

            Statement currentStatement = null;
            while (scanner.hasNext()) {
                String rawStatement = scanner.next() + delimiter;
                try {
                    currentStatement = conn.createStatement();
                    currentStatement.execute(rawStatement);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (currentStatement != null) {
                        try {
                            currentStatement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    currentStatement = null;
                }
            }
            scanner.close();
        }catch (SQLException | RuntimeException e) {
            if (e instanceof SQLException sqlException) {
                System.err.format("SQL State: %s\n%s", sqlException.getSQLState(), sqlException.getMessage());
            } else {
                e.printStackTrace();
            }
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
