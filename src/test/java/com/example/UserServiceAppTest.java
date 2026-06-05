
package com.example;

import com.example.dao.UserDaoImpl;
import com.example.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceAppTest {

    @Mock
    private UserDaoImpl userDao;

    @InjectMocks
    private UserServiceApp userServiceApp;

    @Test
    @DisplayName("Должен успешно создать пользователя и присвоить ему ID")
    void testCreateUser() {
        User user = new User("John Doe", "john@example.com");
        
        
		doAnswer(invocation -> {
			User createdUser = invocation.getArgument(0);
			createdUser.setId(1L);
			return null; 
		}).when(userDao).createUser(any(User.class));

        userServiceApp.createUser(user);

        verify(userDao, times(1)).createUser(user);
    }

    @Test
    @DisplayName("Должен возвращать список всех пользователей")
    void testListUsers() {
        List<User> users = List.of(
                new User("John Doe", "john@example.com"),
                new User("Jane Doe", "jane@example.com")
        );
        when(userDao.getAllUsers()).thenReturn(users);

        List<User> result = userServiceApp.listUsers();

        verify(userDao).getAllUsers();
        assert(result.size() == 2);
    }

    @Test
    @DisplayName("Должен успешно вызвать метод удаления")
    void testDeleteUser() {

        Long userId = 1L;

        userServiceApp.deleteUser(userId);
        verify(userDao).deleteUser(userId);
    }
}

