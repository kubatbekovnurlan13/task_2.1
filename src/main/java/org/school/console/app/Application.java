package org.school.console.app;

import org.school.console.app.service.InitializationOfDatabase;

public class Application {
    public static void main(String[] args) {
        InitializationOfDatabase initDb = new InitializationOfDatabase();

        initDb.init();

//        Student student = new Student("Nurlan", "Kubatbekov", 1);
//        StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
//        studentDAOImplementation.save(student);
//        Course course = new Course("bio","bio_desc");
//        CourseDAOImplementation cDAOI = new CourseDAOImplementation();
//        cDAOI.save(course);
    }
}
