package ru.danilscheglov.util;

/**
 * Утилитный класс для хранения текстовых констант и шаблонов вывода
 *
 * @author Danil Scheglov
 */
public final class UiUtil {

    public static final String MAIN_MENU = """
            =========================================
            |              СПИСОК ДЕЛ               |
            =========================================
             [1] Показать все задачи
             [2] Добавить новую задачу
             [3] Изменить задачу
             [4] Удалить задачу
             [5] Показать только АКТИВНЫЕ задачи
             [6] Показать только ВЫПОЛНЕННЫЕ задачи
             [0] Выйти из приложения
            =========================================
            """;

    public static final String HEADER_ALL = """
            +-----------------------------------+
            |          СПИСОК ВСЕХ ЗАДАЧ        |
            +-----------------------------------+
            """;

    public static final String HEADER_CREATE = """
            +-----------------------------------+
            |      ДОБАВЛЕНИЕ НОВОЙ ЗАДАЧИ      |
            +-----------------------------------+
            """;

    public static final String HEADER_UPDATE = """
            +-----------------------------------+
            |      ИЗМЕНЕНИЕ СТАТУСА ЗАДАЧИ     |
            +-----------------------------------+
            """;

    public static final String HEADER_DELETE = """
            +-----------------------------------+
            |          УДАЛЕНИЕ ЗАДАЧИ          |
            +-----------------------------------+
            """;

    public static final String HEADER_ACTIVE = """
            +-----------------------------------+
            |       СПИСОК АКТИВНЫХ ЗАДАЧ       |
            +-----------------------------------+
            """;

    public static final String HEADER_COMPLETED = """
            +-----------------------------------+
            |     СПИСОК ВЫПОЛНЕННЫХ ЗАДАЧ      |
            +-----------------------------------+
            """;

    public static final String INFO_EMPTY = "[Инфо] Ваш список дел пока пуст. Добавьте первую задачу!";
    public static final String INFO_NO_ACTIVE = "[Инфо] У вас нет активных задач. Отличная работа!";
    public static final String INFO_NO_COMPLETED = "[Инфо] Вы пока не выполнили ни одной задачи. Время начать!";
    public static final String INFO_NOTHING_TO_CHANGE = "[Инфо] Ваш список дел пуст. Нечего изменять!";
    public static final String INFO_NOTHING_TO_DELETE = "[Инфо] Ваш список дел пуст. Нечего удалять!";

    public static String successDelete(int id) {
        return "[Успешно] Задача №" + id + " удалена.";
    }

    public static String successUpdate(int id) {
        return "[Успешно] Статус задачи №" + id + " изменен.";
    }

    public static String errorNotFound(int id) {
        return "[Ошибка] Задача с №" + id + " не найдена!";
    }

    public static void clearConsole() {
        System.out.println("\n".repeat(15));
    }
}
