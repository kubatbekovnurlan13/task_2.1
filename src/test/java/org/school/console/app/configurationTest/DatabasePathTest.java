package org.school.console.app.configurationTest;

import org.junit.jupiter.api.Test;
import org.school.console.app.configuration.DatabasePath;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabasePathTest {

    @Test
    void getSqlDataPath_testGetSqlDataPath_whenThereIsValue() {

        Path actual = DatabasePath.getSqlDataPath();
        Path expected = Paths.get("src/main/resources/sql/data.sql");
        assertEquals(expected, actual);
    }

    @Test
    void getSqlSchemaPath_testGetSqlSchemaPath_whenThereIsValue() {

        Path actual = DatabasePath.getSqlSchemaPath();
        Path expected = Paths.get("src/main/resources/sql/schema.sql");
        assertEquals(expected, actual);
    }
}
