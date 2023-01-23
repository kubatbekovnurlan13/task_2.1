package org.school.console.app.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InitializationOfDb {
    private final String URL = "jdbc:postgresql://localhost:5432/schooldb";
    private final String USER = "postgres";
    private final String PASSWORD = "newpostgres";
    private final Path sqlScriptPath = Paths.get("src/main/resources/creationOfTables.sql");


    public void init() {
        ArrayList<String> tableNames = new ArrayList<>();
        tableNames.add("students_courses");
        tableNames.add("students");
        tableNames.add("courses");
        tableNames.add("groups");

        if (checkIfTablesExists(tableNames)) {
            dropTables(tableNames);
        }
        createTables();
    }

    private void dropTables(ArrayList<String> tableNames) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            Statement statement = conn.createStatement();
            for (String name : tableNames) {
                String sql = "drop table " + name + ";";
                System.out.println("sql  : " + sql);
                statement.execute(sql);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkIfTablesExists(ArrayList<String> tableNames) {
        boolean tableExits = false;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tableExits;
    }

    private void createTables() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String delimiter = ";";

            Scanner scanner = getScanner(delimiter);

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
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Scanner getScanner(String delimiter) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(sqlScriptPath).useDelimiter(delimiter);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return scanner;
    }

}
