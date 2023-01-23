package org.school.console.app;

import org.school.console.app.dao.StudentDAOImplementation;
import org.school.console.app.model.Student;
import org.school.console.app.service.InitializationOfDb;

public class Application {
    public static void main(String[] args) {
        InitializationOfDb initDb = new InitializationOfDb();

        initDb.init();

//        Student student = new Student("Nurlan", "Kubatbekov", 1);
//        StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
//        studentDAOImplementation.save(student);
    }
}