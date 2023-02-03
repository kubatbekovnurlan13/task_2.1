package org.school.console.app.daoInterfaces;

import org.school.console.app.model.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);

    boolean update(Student student);

    Student getById(int student_id);

    List<Student> getAll();

    boolean deleteById(int student_id);

}
