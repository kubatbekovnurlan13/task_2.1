package org.school.console.app.service;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DatabasePath {
    private static final Path sqlSchemaPath = Paths.get("src/main/resources/sql/schema.sql");
    private static final Path sqlTableCreationPath = Paths.get("src/main/resources/sql/creationOfTables.sql");

    public static Path getSqlSchemaPath() {
        return sqlSchemaPath;
    }

    public static Path getSqlTableCreationPath() {
        return sqlTableCreationPath;
    }
}