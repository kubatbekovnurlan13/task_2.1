package org.school.console.app.configurationForConnectionTest;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestDatabasePath {
    private static final Path sqlSchemaPath = Paths.get("src/test/resourcesTest/sqlTest/testSchema.sql");
    private static final Path sqlDataPath = Paths.get("src/test/resourcesTest/sqlTest/testData.sql");
    private static final Path sqlDropPath = Paths.get("src/test/resourcesTest/sqlTest/testDropSchema.sql");

    public static Path getSqlSchemaPath() {
        return sqlSchemaPath;
    }

    public static Path getSqlDataPath() {
        return sqlDataPath;
    }

    public static Path getSqlDropPath() {
        return sqlDropPath;
    }

}
