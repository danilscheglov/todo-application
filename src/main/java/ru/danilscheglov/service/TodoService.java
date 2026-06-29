package ru.danilscheglov.service;

import ru.danilscheglov.exception.TaskNotFoundException;
import ru.danilscheglov.model.Task;
import ru.danilscheglov.repository.TodoRepository;

import java.util.Collection;
import java.util.List;

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

    public void toggleTaskStatus(int id) {
        Task task = todoRepository.getTaskById(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        task.setCompleted(!task.isCompleted());
        todoRepository.updateTask(task);
    }

    public void deleteTask(int id) {
        if (todoRepository.getTaskById(id) == null) {
            throw new TaskNotFoundException(id);
        }
        todoRepository.deleteTaskById(id);
    }

    public List<Task> getActiveTasks() {
        return todoRepository.getAllTask().stream()
                .filter(task -> !task.isCompleted())
                .toList();
    }

    public List<Task> getCompletedTasks() {
        return todoRepository.getAllTask().stream()
                .filter(Task::isCompleted)
                .toList();
    }
}
