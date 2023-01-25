package org.school.console.app.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperties {
    private String URL;
    private String USER;
    private String PASSWORD;

    public DatabaseProperties() {
        initializeProperties();
    }

    private void initializeProperties() {
        try (InputStream input = new FileInputStream("src/main/resources/dbProperies.properties")) {
            Properties prop = new Properties();

            prop.load(input);

            URL = prop.getProperty("URL");
            USER = prop.getProperty("USER");
            PASSWORD = prop.getProperty("PASSWORD");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getURL() {
        return URL;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
}
