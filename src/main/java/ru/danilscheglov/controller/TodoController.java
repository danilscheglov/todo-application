package ru.danilscheglov.controller;

import ru.danilscheglov.model.Task;
import ru.danilscheglov.service.TodoService;

import java.util.Collection;
import java.util.Scanner;

import static ru.danilscheglov.util.InputUtil.readInt;
import static ru.danilscheglov.util.UiRenderer.checkEmpty;
import static ru.danilscheglov.util.UiRenderer.printOperationsResult;
import static ru.danilscheglov.util.UiUtil.*;

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
                case "5" -> showActiveTasks();
                case "6" -> showCompletedTasks();
                case "0" -> {
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
        System.out.println(HEADER_ALL);

        if (checkEmpty(tasks, INFO_EMPTY)) return;

        for (Task task : tasks) {
            String statusMarker = task.isCompleted() ? "[X]" : "[ ]";
            System.out.println(task.getId() + ". " + statusMarker + " " + task.getName() + " (Описание: " + task.getDescription() + ")");
        }
    }

    private void createTask() {
        clearConsole();
        System.out.println(HEADER_CREATE);

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
        System.out.println(HEADER_UPDATE);

        if (checkEmpty(todoService.getAllTasks(), INFO_NOTHING_TO_CHANGE)) return;

        int id = readInt(scanner, "Введите номер задачи » ");
        boolean success = todoService.toggleTaskStatus(id);

        if (success) {
            System.out.println("[Успешно] Статус задачи №" + id + " изменен.");
        } else {
            System.out.println("[Ошибка] Задача с №" + id + " не найдена!");
        }
        printOperationsResult(success, successUpdate(id), errorNotFound(id));
    }

    private void deleteTask() {
        clearConsole();
        System.out.println(HEADER_DELETE);

        if (checkEmpty(todoService.getAllTasks(), INFO_NOTHING_TO_DELETE)) return;

        int id = readInt(scanner, "Введите номер задачи для удаления » ");
        boolean success = todoService.deleteTask(id);

        printOperationsResult(success, successDelete(id), errorNotFound(id));
    }

    private void showActiveTasks() {
        clearConsole();
        Collection<Task> tasks = todoService.getActiveTasks();

        System.out.println(HEADER_ACTIVE);

        if (checkEmpty(tasks, INFO_NO_ACTIVE)) return;

        for (Task task : tasks) {
            System.out.println(task.getId() + ". [ ] " + task.getName() + " - " + task.getDescription());
        }
    }

    private void showCompletedTasks() {
        clearConsole();
        Collection<Task> tasks = todoService.getCompletedTasks();
        System.out.println(HEADER_COMPLETED);

        if (checkEmpty(tasks, INFO_NO_COMPLETED)) return;

        for (Task task : tasks) {
            System.out.println(task.getId() + ". [X] " + task.getName() + " - " + task.getDescription());
        }
    }

    private void printMenu() {
        System.out.println(MAIN_MENU);
        System.out.print("Выберите действие » ");
    }
}