package com.example;

import com.example.dao.UserDaoImpl;
import com.example.model.User;
import com.example.util.HibernateUtil;
import lombok.*;

import java.util.List;
import java.util.Scanner;

public class UserServiceApp {
    private static UserDaoImpl userDao = new UserDaoImpl(HibernateUtil.getSessionFactory());
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Меню ---");
            System.out.println("Введите команду: create, read, update, delete, list, exit");
            String command = scanner.nextLine().toLowerCase().trim();

            switch (command) {
                case "create":
                    createUser();
                    break;
                case "read":
                    readUser();
                    break;
                case "update":
                    updateUser();
                    break;
                case "delete":
                    deleteUser();
                    break;
                case "list":
                    listUsers();
                    break;
                case "exit":
                    System.out.println("Выход из приложения...");
                    System.exit(0);
                default:
                    System.out.println("Неизвестная команда");
            }
        }
    }

    private static void createUser() {
		User user = new User();
		System.out.print("Введите имя: ");
		user.setName(scanner.nextLine());
		System.out.print("Введите email: ");
		user.setEmail(scanner.nextLine());

    try {
        userDao.createUser(user);
        System.out.println("Пользователь успешно создан! ID: " + user.getId());
    } catch (Exception e) {
			System.out.println("Ошибка при создании пользователя.");
			e.printStackTrace();
		}
	}

	private static void readUser() {
		System.out.print("Введите ID пользователя: ");
    try {
        Long id = Long.parseLong(scanner.nextLine());
        User user = userDao.getUser(id);
     
    } catch (Exception e) {
        System.out.println("Ошибка при чтении пользователя.");
		e.printStackTrace();
    }
}

	private static void updateUser() {
		System.out.print("Введите ID пользователя для обновления: ");
    try {
        Long id = Long.parseLong(scanner.nextLine());
        User user = userDao.getUser(id);

        if (user != null) {
            System.out.print("Введите новое имя (текущее: " + user.getName() + "): ");
            user.setName(scanner.nextLine());
            System.out.print("Введите новый email (текущий: " + user.getEmail() + "): ");
            user.setEmail(scanner.nextLine());

            try {
					userDao.updateUser(user);
					System.out.println("Данные обновлены!");
				} catch (Exception e) {
					System.out.println("Ошибка при обновлении пользователя.");
                e.printStackTrace();
					}
			} else {
				System.out.println("Пользователь не найден.");
			}
		} catch (NumberFormatException e) {
        System.out.println("Ошибка: ID должен быть числом.");
		e.printStackTrace();
    }
}

	private static void deleteUser() {
		System.out.print("Введите ID пользователя для удаления: ");
    try {
        Long id = Long.parseLong(scanner.nextLine());
        try {
            userDao.deleteUser(id);
            System.out.println("Команда на удаление отправлена (если ID существовал, он удален).");
        } catch (Exception e) {
            System.out.println("Ошибка при удалении пользователя.");
            e.printStackTrace();
        }
    } catch (NumberFormatException e) {
        System.out.println("Ошибка: ID должен быть числом.");
		e.printStackTrace();
    } 
}

    private static void listUsers() {
        List<User> users = userDao.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Список пользователей пуст.");
        } else {
            System.out.println("Список всех пользователей:");
            users.forEach(System.out::println);
        }
    }
}

   