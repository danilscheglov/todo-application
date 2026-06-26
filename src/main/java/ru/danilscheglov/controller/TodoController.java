package ru.danilscheglov.controller;

import ru.danilscheglov.model.Task;
import ru.danilscheglov.repository.TodoRepository;

import java.util.Collection;
import java.util.Scanner;

/**
 * @author Danil Scheglov
 */
public class TodoController {

    private final Scanner scanner = new Scanner(System.in);
    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
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
        Collection<Task> tasks = todoRepository.getAllTask();
        System.out.println("""
                +-----------------------------------+
                |          СПИСОК ВСЕХ ЗАДАЧ        |
                +-----------------------------------+
                """);

        if (tasks.isEmpty()) {
            clearConsole();
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

        todoRepository.createTask(new Task(name, description));
        System.out.println("[Успешно] Задача создана и сохранена.");
    }

    private void updateTask() {
        clearConsole();
        System.out.println("""
                +-----------------------------------+
                |      ИЗМЕНЕНИЕ СТАТУСА ЗАДАЧИ     |
                +-----------------------------------+
                """);

        if (todoRepository.getAllTask().isEmpty()) {
            System.out.println("[Инфо] Ваш список дел пуст. Нечего изменять!");
            return;
        }

        int id = readInt("Введите номер задачи » ");
        Task task = todoRepository.getTaskById(id);

        if (task == null) {
            System.out.println("[Ошибка] Задача с №" + id + " не найдена!");
            return;
        }

        task.setCompleted(!task.isCompleted());
        todoRepository.updateTask(task);
        System.out.println("[Успешно] Статус задачи №" + id + " изменен.");
    }

    private void deleteTask() {
        System.out.println("""
                +-----------------------------------+
                |          УДАЛЕНИЕ ЗАДАЧИ          |
                +-----------------------------------+
                """);

        if (todoRepository.getAllTask().isEmpty()) {
            System.out.println("[Инфо] Ваш список дел пуст. Нечего удалять!");
            return;
        }

        int id = readInt("Введите номер задачи для удаления » ");
        Task task = todoRepository.getTaskById(id);

        if (task == null) {
            System.out.println("[Ошибка] Задача с №" + id + " не найдена!");
            return;
        }

        todoRepository.deleteTaskById(task.getId());
        System.out.println("[Успешно] Задача №" + id + " («" + task.getName() + "») удалена.");
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