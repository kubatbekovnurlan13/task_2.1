package org.school.console.app.configurationTest;

import org.junit.jupiter.api.Test;
import org.school.console.app.configuration.DatabaseProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabasePropertiesTest {
    private final DatabaseProperties databaseProperties = new DatabaseProperties();

    @Test
    void getURL_testGetURL_whenThereIsValue() {
        String actual = databaseProperties.getURL();
        String expected = "jdbc:postgresql://localhost:5432/schooldb";
        assertEquals(expected, actual);
    }

    @Test
    void getUSER_testGetUSER_whenThereIsValue() {
        String actual = databaseProperties.getUSER();
        String expected = "postgres";
        assertEquals(expected, actual);
    }

    @Test
    void getPASSWORD_testGetPASSWORD_whenThereIsValue() {
        String actual = databaseProperties.getPASSWORD();
        String expected = "newpostgres";
        assertEquals(expected, actual);
    }
}
