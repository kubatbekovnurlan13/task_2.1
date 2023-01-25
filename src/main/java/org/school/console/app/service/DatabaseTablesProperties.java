package org.school.console.app.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseTablesProperties {
    public static ArrayList<String> getTables() {
        ArrayList<String> listOfTables = new ArrayList<>();

        try (InputStream input = new FileInputStream("src/main/resources/tablesForCheckAndDrop.properties")) {
            Properties prop = new Properties();

            prop.load(input);

            for (int i = 1; i <= prop.size(); i++) {
                listOfTables.add(prop.getProperty(String.valueOf(i)));
            }

            return listOfTables;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listOfTables;
    }
}
