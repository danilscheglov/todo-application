package ru.danilscheglov.repository;

import ru.danilscheglov.model.Task;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Danil Scheglov
 */
public class TodoRepository {

    private final Map<Integer, Task> tasks = new HashMap<>();

    private int idCounter = 1;

    public void createTask(Task task) {
        task.setId(idCounter);
        tasks.put(task.getId(), task);
        idCounter++;
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Collection<Task> getAllTask() {
        return tasks.values();
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }
}
