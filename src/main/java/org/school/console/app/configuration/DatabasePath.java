package org.school.console.app.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DatabasePath {
    private static final Path sqlSchemaPath = Paths.get("src/main/resources/sql/schema.sql");
    private static final Path sqlDataPath = Paths.get("src/main/resources/sql/data.sql");

    public static Path getSqlSchemaPath() {
        return sqlSchemaPath;
    }

    public static Path getSqlDataPath() {
        return sqlDataPath;
    }
}