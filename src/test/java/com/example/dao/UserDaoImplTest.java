
package com.example.dao;

import com.example.model.User;
import com.example.util.HibernateUtil;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoImplTest {
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    private UserDaoImpl userDao;

    @BeforeAll
    static void setupAll() {
        postgresContainer.start();
        HibernateUtil.setSessionFactory(
                postgresContainer.getJdbcUrl(), 
                postgresContainer.getUsername(), 
                postgresContainer.getPassword()
        );
    }

    @AfterAll
    static void tearDownAll() {
        postgresContainer.stop();
    }

    @BeforeEach
    public void setUp() {
        userDao = new UserDaoImpl(HibernateUtil.getSessionFactory());

        cleanDatabase();
    }

    private void cleanDatabase() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    @DisplayName("Создание пользователя должно работать и генерировать ID")
    public void testCreateUser() {
        User user = new User();
        user.setName("Test User");
        user.setEmail("test@example.com");

        userDao.createUser(user);
        assertNotNull(user.getId(), "ID должен быть сгенерирован после сохранения");
    }

    @Test
    @DisplayName("Получение пользователя по ID должно возвращать верные данные")
    public void testGetUser() {
        User user = new User();
        user.setName("Test User");
        user.setEmail("test@example.com");
        userDao.createUser(user);

        User retrievedUser = userDao.getUser(user.getId());
        
        assertNotNull(retrievedUser);
        assertEquals(user.getName(), retrievedUser.getName());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
    }

    @Test
    @DisplayName("Удаление пользователя должно делать его недоступным")
    public void testDeleteUser() {
        User user = new User();
        user.setName("Delete Me");
        user.setEmail("delete@example.com");
        userDao.createUser(user);

        userDao.deleteUser(user.getId());
        assertNull(userDao.getUser(user.getId()), "Пользователь должен быть удален из базы");
    }
}
