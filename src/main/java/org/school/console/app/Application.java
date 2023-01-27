package org.school.console.app;

import org.school.console.app.dao.CourseDAOImplementation;
import org.school.console.app.dao.StudentDAOImplementation;
import org.school.console.app.model.Course;
import org.school.console.app.model.Student;
import org.school.console.app.configuration.DatabaseInitialization;

public class Application {
    public static void main(String[] args) {
        DatabaseInitialization initDb = new DatabaseInitialization();
        initDb.init();

        Student student = new Student("Nurlan", "Kubatbekov", 1);
        StudentDAOImplementation studentDAOImplementation = new StudentDAOImplementation();
        studentDAOImplementation.save(student);
        Course course = new Course("bio","bio_desc");
        CourseDAOImplementation cDAOI = new CourseDAOImplementation();
        cDAOI.save(course);
    }
}
