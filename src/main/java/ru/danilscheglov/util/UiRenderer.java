package ru.danilscheglov.util;

import ru.danilscheglov.model.Task;

import java.util.Collection;

/**
 * Утилитный класс для стандартизации вывода результатов операций и проверок в UI
 *
 * @author Danil Scheglov
 */
public final class UiRenderer {

    /**
     * Проверяет список на пустоту. Если пуст — печатает переданное сообщение
     *
     * @return true, если список пуст, иначе false
     */
    public static boolean checkEmpty(Collection<Task> tasks, String message) {
        if (tasks.isEmpty()) {
            System.out.println(message);
            return true;
        }
        return false;
    }
}
