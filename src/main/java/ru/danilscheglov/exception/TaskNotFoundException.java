package ru.danilscheglov.exception;

import ru.danilscheglov.util.UiUtil;

/**
 * @author Danil Scheglov
 */
public class TaskNotFoundException extends TodoApplicationException {

    private final int taskId;

    public TaskNotFoundException(int taskId) {
        super(UiUtil.errorNotFound(taskId));
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }
}
