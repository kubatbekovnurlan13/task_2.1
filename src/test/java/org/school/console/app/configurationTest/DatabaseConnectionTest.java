package org.school.console.app.configurationTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.school.console.app.configuration.DatabaseConnection;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DatabaseConnectionTest {
    @Test
    void getConnection_testGetConnection_whenNothingIsWrong() {

        boolean actual = true;
        try {
            actual = !DatabaseConnection.getConnection().isClosed();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        boolean expected = true;
        assertEquals(expected, actual);
    }
}
