package org.school.console.app.daoTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.school.console.app.dao.GroupDAOImplementation;
import org.school.console.app.model.Group;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class GroupDAOImplementationTest {

    @Mock
    private GroupDAOImplementation groupDAOImplementation;
    @Mock
    private Group group;

    @Test
    void save_testSave_whenInputNull() {
        GroupDAOImplementation groupDAOImplementation1 = new GroupDAOImplementation();
        Exception exception = assertThrows(NullPointerException.class,
                () -> groupDAOImplementation1.save(null));
        assertEquals("Cannot invoke \"org.school.console.app.model.Group.getGroup_name()\" because \"group\" is null", exception.getMessage());
    }

    @Test
    void update_testUpdateOfValue_whenValueInput() {
        Mockito.when(groupDAOImplementation.update(group)).thenReturn(true);

        boolean actual = groupDAOImplementation.update(group);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenValueInputNotUpdated() {
        Mockito.when(groupDAOImplementation.update(group)).thenReturn(false);

        boolean actual = groupDAOImplementation.update(group);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenNullInput() {
        GroupDAOImplementation groupDAOImplementation1 = new GroupDAOImplementation();
        Exception exception = assertThrows(NullPointerException.class,
                () -> groupDAOImplementation1.update(null));
        assertEquals("Cannot invoke \"org.school.console.app.model.Group.getGroup_name()\" because \"group\" is null", exception.getMessage());
    }

    @Test
    void getById_testGetById_whenIdInput() {
        Mockito.when(groupDAOImplementation.getById(group.getGroup_id())).thenReturn(group);

        Group actual = groupDAOImplementation.getById(group.getGroup_id());
        Group expected = group;
        assertEquals(expected, actual);
    }

    @Test
    void getAll_testGetAllValues_whenThereIsValues() {
        Mockito.when(groupDAOImplementation.getAll()).thenReturn(List.of(group));

        List<Group> actual = groupDAOImplementation.getAll();
        List<Group> expected = List.of(group);

        assertEquals(expected, actual);
    }

    @Test
    void deleteById_testDeleteById_whenThereIsValue() {
        Mockito.when(groupDAOImplementation.deleteById(group.getGroup_id())).thenReturn(true);

        boolean actual = groupDAOImplementation.deleteById(group.getGroup_id());
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    void deleteById_testDeleteValueById_whenValueNotDeleted() {
        Mockito.when(groupDAOImplementation.deleteById(group.getGroup_id())).thenReturn(false);

        boolean actual = groupDAOImplementation.deleteById(group.getGroup_id());
        boolean expected = false;

        assertEquals(expected, actual);
    }
}
