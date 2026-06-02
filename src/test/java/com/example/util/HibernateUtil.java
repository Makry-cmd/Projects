
package com.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            // Попытка инициализации стандартным способом (через hibernate.cfg.xml)
            // Это сработает в основном приложении
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // В тестах это ожидаемо, так как конфига может не быть или он не подходит
            System.err.println("Initial Hibernate initialization skipped/failed: " + ex.getMessage());
        }
    }

    /**
     * Метод, который требует твой тест.
     * Создает SessionFactory с нуля, используя параметры из Testcontainers.
     */
    public static void setSessionFactory(String url, String user, String password) {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }

        try {
            Configuration configuration = new Configuration();

            // Вместо .configure() (который читает XML), 
            // мы задаем настройки программно, чтобы гарантировать работу с контейнером
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "org.postgresql.Driver");
            settings.put(Environment.URL, url);
            settings.put(Environment.USER, user);
            settings.put(Environment.PASS, password);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.HBM2DDL_AUTO, "update"); // Авто-создание таблиц

            configuration.setProperties(settings);

            // ВАЖНО: Если ты не используешь hibernate.cfg.xml, 
            // тебе нужно вручную зарегистрировать все свои Entity-классы здесь:
            // configuration.addAnnotatedClass(com.example.model.User.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Test SessionFactory: " + e.getMessage(), e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
