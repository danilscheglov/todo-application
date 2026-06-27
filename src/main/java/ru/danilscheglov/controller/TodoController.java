package ru.danilscheglov.controller;

import ru.danilscheglov.model.Task;
import ru.danilscheglov.service.TodoService;

import java.util.Collection;
import java.util.Scanner;

/**
 * @author Danil Scheglov
 */
public class TodoController {

    private final Scanner scanner = new Scanner(System.in);
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    public void start() {
        clearConsole();
        while (true) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> showAllTasks();
                case "2" -> createTask();
                case "3" -> updateTask();
                case "4" -> deleteTask();
                case "5" -> {
                    System.out.println("До скорой встречи!");
                    System.exit(0);
                }
                default -> System.out.println("Команда не распознана! Введите цифру от 1 до 5.");
            }
        }
    }

    private void showAllTasks() {
        clearConsole();
        Collection<Task> tasks = todoService.getAllTasks();
        System.out.println("""
                +-----------------------------------+
                |          СПИСОК ВСЕХ ЗАДАЧ        |
                +-----------------------------------+
                """);

        if (tasks.isEmpty()) {
            System.out.println("[Инфо] Ваш список дел пока пуст. Добавьте первую задачу!");
            return;
        }

        for (Task task : tasks) {
            String statusMarker = task.isCompleted() ? "[X]" : "[ ]";
            System.out.println(task.getId() + ". " + statusMarker + " " + task.getName() + " (Описание: " + task.getDescription() + ")");
        }
    }

    private void createTask() {
        clearConsole();
        System.out.println("""
                +-----------------------------------+
                |      ДОБАВЛЕНИЕ НОВОЙ ЗАДАЧИ      |
                +-----------------------------------+
                """);

        System.out.println("Шаг 1. Введите название задачи:");
        System.out.print("» ");
        String name = scanner.nextLine();

        System.out.println("Шаг 2. Введите краткое описание:");
        System.out.print("» ");
        String description = scanner.nextLine();

        todoService.createTask(name, description);
        System.out.println("[Успешно] Задача создана и сохранена.");
    }

    private void updateTask() {
        clearConsole();
        System.out.println("""
                +-----------------------------------+
                |      ИЗМЕНЕНИЕ СТАТУСА ЗАДАЧИ     |
                +-----------------------------------+
                """);

        if (todoService.getAllTasks().isEmpty()) {
            System.out.println("[Инфо] Ваш список дел пуст. Нечего изменять!");
            return;
        }

        int id = readInt("Введите номер задачи » ");

        boolean success = todoService.toggleTaskStatus(id);

        if (success) {
            System.out.println("[Успешно] Статус задачи №" + id + " изменен.");
        } else {
            System.out.println("[Ошибка] Задача с №" + id + " не найдена!");
        }
    }

    private void deleteTask() {
        System.out.println("""
                +-----------------------------------+
                |          УДАЛЕНИЕ ЗАДАЧИ          |
                +-----------------------------------+
                """);

        if (todoService.getAllTasks().isEmpty()) {
            System.out.println("[Инфо] Ваш список дел пуст. Нечего удалять!");
            return;
        }

        int id = readInt("Введите номер задачи для удаления » ");
        boolean success = todoService.deleteTask(id);

        if (!success) {
            System.out.println("[Ошибка] Задача с №" + id + " не найдена!");
            return;
        }

        System.out.println("[Успешно] Задача №" + id + " удалена.");
    }

    private int readInt(String promptMessage) {
        while (true) {
            System.out.print(promptMessage);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[Ошибка] Неверный формат! Пожалуйста, вводите только цифры.");
                System.out.println("--------------------------------------------------");
            }
        }
    }

    private void clearConsole() {
        System.out.println("\n".repeat(15));
    }

    private void printMenu() {
        System.out.println("""
                =========================================
                |              СПИСОК ДЕЛ               |
                =========================================
                 [1] Показать все задачи
                 [2] Добавить новую задачу
                 [3] Изменить задачу
                 [4] Удалить задачу
                 [5] Выйти из приложения
                =========================================
                """);
        System.out.print("Выберите действие » ");
    }
}