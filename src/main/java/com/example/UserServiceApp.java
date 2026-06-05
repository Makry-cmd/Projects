
package com.example;

import com.example.dao.UserDaoImpl;
import com.example.model.User;
import com.example.util.HibernateUtil;
import java.util.List;
import java.util.Scanner;

public class UserServiceApp {
    private final UserDaoImpl userDao;
    private final Scanner scanner = new Scanner(System.in);

    public UserServiceApp(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public static void main(String[] args) {
        UserDaoImpl realDao = new UserDaoImpl(HibernateUtil.getSessionFactory());
        UserServiceApp app = new UserServiceApp(realDao);
        app.runMenu();
    }

    public void runMenu() {
        while (true) {
            System.out.println("\n--- Меню ---");
            System.out.println("Введите команду: create, read, update, delete, list, exit");
            String command = scanner.nextLine().toLowerCase().trim();

            switch (command) {
				case "create":
					handleCreate();
				break;
				case "read":
					handleRead();
				break;
				case "update":
					handleUpdate();
				break;
				case "delete":
					handleDelete();
				break;
				case "list":
					handleList();
				break;
				case "exit":
					System.out.println("Выход...");
				return; 
			default:
				System.out.println("Неизвестная команда");
			break;
}
        }
    }

    public void createUser(User user) {
        userDao.createUser(user);
    }

    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    public List<User> listUsers() {
        return userDao.getAllUsers();
    }
	
    private void handleCreate() {
        User user = new User();
        System.out.print("Введите имя: ");
        user.setName(scanner.nextLine());
        System.out.print("Введите email: ");
        user.setEmail(scanner.nextLine());
        try {
            createUser(user);
            System.out.println("Пользователь создан! ID: " + user.getId());
        } catch (Exception e) {
            System.out.println("Ошибка при создании.");
        }
    }

    private void handleRead() {
        System.out.print("Введите ID: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            User user = getUser(id);
            System.out.println(user != null ? user : "Не найден");
        } catch (Exception e) {
            System.out.println("Ошибка чтения.");
        }
    }

    private void handleUpdate() {
        System.out.print("Введите ID для обновления: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            User user = getUser(id);
            if (user != null) {
                System.out.print("Новое имя: ");
                user.setName(scanner.nextLine());
                System.out.print("Новый email: ");
                user.setEmail(scanner.nextLine());
                updateUser(user);
                System.out.println("Обновлено!");
            } else {
                System.out.println("Не найден.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка обновления.");
        }
    }

    private void handleDelete() {
        System.out.print("Введите ID для удаления: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            deleteUser(id);
            System.out.println("Удалено.");
        } catch (Exception e) {
            System.out.println("Ошибка удаления.");
        }
    }

    private void handleList() {
        List<User> users = listUsers();
        if (users.isEmpty()) {
            System.out.println("Список пуст.");
        } else {
            users.forEach(System.out::println);
        }
    }
}


   