package org.school.console.app.configurationTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DatabaseConnectionTest {
    @Test
    void testMockDBConnection() throws Exception {
        TestDatabaseInitialization.createTables();

        int value = TestDatabase
                .getConnection()
                .createStatement()
                .executeUpdate("update testTable set testTable_name='New Name 1' where testTable_id=1");

        assertEquals(value, 1);
    }

}
