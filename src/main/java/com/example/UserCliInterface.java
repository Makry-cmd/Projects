package com.example;

import com.example.service.UserService;
import com.example.dto.UserDto;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class UserCliInterface {

    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);
	private final ConfigurableApplicationContext context;
	
    public UserCliInterface(UserService userService,ConfigurableApplicationContext context) {
        this.userService = userService;
		this.context = context;
    }

    public void runMenu() {
        while (true) {
            System.out.println("\n--- User Management (DTO Mode) ---");
            System.out.println("Введите команду: create, read, update, delete, list, exit");
            String command = scanner.nextLine().toLowerCase().trim();

            switch (command) {
                case "create" -> handleCreate();
                case "read" -> handleRead();
                case "update" -> handleUpdate();
                case "delete" -> handleDelete();
                case "list" -> handleList();
                case "exit" -> {
                    System.out.println("Выход...");
					context.close();
                    return;
                }
                default -> System.out.println("Неизвестная команда");
            }
        }
    }

    private void handleCreate() {
        UserDto dto = new UserDto();
        System.out.print("Введите имя: ");
        dto.setName(scanner.nextLine());
        System.out.print("Введите email: ");
        dto.setEmail(scanner.nextLine());

        try {
            UserDto created = userService.createUser(dto);
            System.out.println("Пользователь создан! ID: " + created.getId());
        } catch (Exception e) {
            System.out.println("Ошибка при создании: " + e.getMessage());
        }
    }

    private void handleRead() {
        System.out.print("Введите ID: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());    UserDto userDto = userService.getUserById(id);
            System.out.println(userDto);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом.");
        } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void handleUpdate() {
        System.out.print("Введите ID для обновления: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            userService.getUserById(id);

            UserDto dto = new UserDto();
            System.out.print("Новое имя: ");
            dto.setName(scanner.nextLine());
            System.out.print("Новый email: ");
            dto.setEmail(scanner.nextLine());

            UserDto updated = userService.updateUser(id, dto);
            System.out.println("Обновлено: " + updated);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом.");
        } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void handleDelete() {
        System.out.print("Введите ID для удаления: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            userService.deleteUser(id);
            System.out.println("Удалено успешно.");
        } catch (Exception e) {
            System.out.println("Ошибка удаления: " + e.getMessage());
        }
    }

    private void handleList() {
        List<UserDto> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Список пуст.");
        } else {
            System.out.println("Список пользователей:");
            users.forEach(System.out::println);
        }
    }
}
