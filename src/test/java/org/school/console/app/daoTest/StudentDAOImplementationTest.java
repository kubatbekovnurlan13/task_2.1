package org.school.console.app.daoTest;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.school.console.app.dao.GroupDAOImplementation;
import org.school.console.app.dao.StudentDAOImplementation;
import org.school.console.app.model.Group;
import org.school.console.app.model.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class StudentDAOImplementationTest {

    @Mock
    private StudentDAOImplementation studentDAOImplementation;
    @Mock
    private Student student;

    @Test
    void save_testSave_whenInputNull() {
        StudentDAOImplementation studentDAOImplementation1 = new StudentDAOImplementation();
        Exception exception = assertThrows(NullPointerException.class,
                () -> studentDAOImplementation1.save(null));
        assertEquals("Cannot invoke \"org.school.console.app.model.Student.getFirst_name()\" because \"student\" is null", exception.getMessage());
    }

    @Test
    void update_testUpdateOfValue_whenValueInput() {
        Mockito.when(studentDAOImplementation.update(student)).thenReturn(true);

        boolean actual = studentDAOImplementation.update(student);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenValueInputNotUpdated() {
        Mockito.when(studentDAOImplementation.update(student)).thenReturn(false);

        boolean actual = studentDAOImplementation.update(student);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenNullInput() {
        StudentDAOImplementation studentDAOImplementation1 = new StudentDAOImplementation();
        Exception exception = assertThrows(NullPointerException.class,
                () -> studentDAOImplementation1.update(null));
        assertEquals("Cannot invoke \"org.school.console.app.model.Student.getFirst_name()\" because \"student\" is null", exception.getMessage());
    }

    @Test
    void getById_testGetById_whenIdInput() {
        Mockito.when(studentDAOImplementation.getById(student.getStudent_id())).thenReturn(student);

        Student actual = studentDAOImplementation.getById(student.getStudent_id());
        Student expected = student;
        assertEquals(expected, actual);
    }

    @Test
    void getAll_testGetAllValues_whenThereIsValues() {
        Mockito.when(studentDAOImplementation.getAll()).thenReturn(List.of(student));

        List<Student> actual = studentDAOImplementation.getAll();
        List<Student> expected = List.of(student);

        assertEquals(expected, actual);
    }

    @Test
    void deleteById_testDeleteById_whenThereIsValue() {
        Mockito.when(studentDAOImplementation.deleteById(student.getStudent_id())).thenReturn(true);

        boolean actual = studentDAOImplementation.deleteById(student.getStudent_id());
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    void deleteById_testDeleteValueById_whenValueNotDeleted() {
        Mockito.when(studentDAOImplementation.deleteById(student.getStudent_id())).thenReturn(false);

        boolean actual = studentDAOImplementation.deleteById(student.getStudent_id());
        boolean expected = false;

        assertEquals(expected, actual);
    }

}
