package org.school.console.app.configurationTest;

import org.junit.jupiter.api.Test;
import org.school.console.app.configuration.DatabaseTablesProperties;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTablesPropertiesTest {
    @Test
    void getTables_testGetTables_whenThereIsValue() {
        List<String> actual = DatabaseTablesProperties.getTables();
        List<String> expected = new ArrayList<>(
                List.of("students_courses",
                        "students",
                        "courses",
                        "groups"));

        assertEquals(expected, actual);
    }
}
