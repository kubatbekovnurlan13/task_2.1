package org.school.console.app;

import org.school.console.app.configuration.DatabaseInitialization;

public class Application {
    public static void main(String[] args) {
        DatabaseInitialization initDb = new DatabaseInitialization();
        initDb.init();
    }
}
