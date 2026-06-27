package ru.danilscheglov.service;

import ru.danilscheglov.model.Task;
import ru.danilscheglov.repository.TodoRepository;

import java.util.Collection;

/**
 * @author Danil Scheglov
 */
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Collection<Task> getAllTasks() {
        return todoRepository.getAllTask();
    }

    public void createTask(String name, String description) {
        Task task = new Task(name, description);
        todoRepository.createTask(task);
    }

    public boolean toggleTaskStatus(int id) {
        Task task = todoRepository.getTaskById(id);
        if (task == null) {
            return false;
        }

        task.setCompleted(!task.isCompleted());
        todoRepository.updateTask(task);
        return true;
    }

    public boolean deleteTask(int id) {
        Task task = todoRepository.getTaskById(id);
        if (task == null) {
            return false;
        }

        todoRepository.deleteTaskById(id);
        return true;
    }
}
