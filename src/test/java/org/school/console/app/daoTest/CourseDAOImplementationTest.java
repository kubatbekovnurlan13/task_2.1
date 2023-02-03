package org.school.console.app.daoTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.school.console.app.dao.CourseDAOImplementation;
import org.school.console.app.model.Course;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CourseDAOImplementationTest {
    @Mock
    private CourseDAOImplementation courseDAOImplementation;
    @Mock
    private Course course;

    @Test
    void save_testSave_whenInputNull() {
        CourseDAOImplementation courseDAOImplementation1 = new CourseDAOImplementation();
        Exception exception = assertThrows(NullPointerException.class,
                () -> courseDAOImplementation1.save(null));
        assertEquals("Cannot invoke \"org.school.console.app.model.Course.getCourse_name()\" because \"course\" is null", exception.getMessage());
    }

    @Test
    void update_testUpdateOfValue_whenValueInput() {
        Mockito.when(courseDAOImplementation.update(course)).thenReturn(true);

        boolean actual = courseDAOImplementation.update(course);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenValueInputNotUpdated() {
        Mockito.when(courseDAOImplementation.update(course)).thenReturn(false);

        boolean actual = courseDAOImplementation.update(course);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenNullInput() {
        CourseDAOImplementation courseDAOImplementation1 = new CourseDAOImplementation();
        Exception exception = assertThrows(NullPointerException.class,
                () -> courseDAOImplementation1.update(null));
        assertEquals("Cannot invoke \"org.school.console.app.model.Course.getCourse_name()\" because \"course\" is null", exception.getMessage());
    }

    @Test
    void getById_testGetCourseById_whenCourseIdInput() {
        Mockito.when(courseDAOImplementation.getById(course.getCourse_id())).thenReturn(course);

        Course actual = courseDAOImplementation.getById(course.getCourse_id());
        Course expected = course;
        assertEquals(expected, actual);
    }

    @Test
    void getAll_testGetAllCourses_whenThereIsCourses() {
        Mockito.when(courseDAOImplementation.getAll()).thenReturn(List.of(course));

        List<Course> actual = courseDAOImplementation.getAll();
        List<Course> expected = List.of(course);

        assertEquals(expected, actual);
    }

    @Test
    void deleteById_testDeleteCourseById_whenThereIsCourse() {
        Mockito.when(courseDAOImplementation.deleteById(course.getCourse_id())).thenReturn(true);

        boolean actual = courseDAOImplementation.deleteById(course.getCourse_id());
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    void deleteById_testDeleteCourseById_whenCourseNotDeleted() {
        Mockito.when(courseDAOImplementation.deleteById(course.getCourse_id())).thenReturn(false);

        boolean actual = courseDAOImplementation.deleteById(course.getCourse_id());
        boolean expected = false;

        assertEquals(expected, actual);
    }

}
