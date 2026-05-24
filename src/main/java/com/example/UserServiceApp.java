   package com.example;

   import com.example.dao.UserDaoImpl;
   import com.example.model.User;

   import java.util.List;
   import java.util.Scanner;

   public class UserServiceApp {
       private static UserDaoImpl userDao = new UserDaoImpl();

       public static void main(String[] args) {
           Scanner scanner = new Scanner(System.in);
           while (true) {
               System.out.println("Введите команду: create, read, update, delete, list, exit");
               String command = scanner.nextLine();

               switch (command) {
                   case "create":
                       // Запрос данных для создания пользователя
                       break;
                   case "read":
                       // Запрос ID для чтения пользователя
                       break;
                   case "update":
                       // Запрос ID и новых данных для обновления пользователя
                       break;
                   case "delete":
                       // Запрос ID для удаления пользователя
                       break;
                   case "list":
                       // Получение списка пользователей
                       break;
                   case "exit":
                       System.exit(0);
                   default:
                       System.out.println("Неизвестная команда");
               }
           }
       }
   }
   