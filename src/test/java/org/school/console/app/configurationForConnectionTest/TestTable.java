package org.school.console.app.configurationForConnectionTest;

import java.util.Objects;

public class TestTable {
    private int testTable_id;
    private String testTable_name;

    public TestTable(int testTable_id, String testTable_name) {
        this.testTable_id = testTable_id;
        this.testTable_name = testTable_name;
    }

    public TestTable() {
    }

    public int getTestTable_id() {
        return testTable_id;
    }

    public void setTestTable_id(int testTable_id) {
        this.testTable_id = testTable_id;
    }

    public String getTestTable_name() {
        return testTable_name;
    }

    public void setTestTable_name(String testTable_name) {
        this.testTable_name = testTable_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestTable testTable = (TestTable) o;
        return testTable_id == testTable.testTable_id && Objects.equals(testTable_name, testTable.testTable_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testTable_id, testTable_name);
    }
}
