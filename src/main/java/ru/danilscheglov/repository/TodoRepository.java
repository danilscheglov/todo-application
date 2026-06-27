package ru.danilscheglov.repository;

import ru.danilscheglov.model.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Danil Scheglov
 */
public class TodoRepository {

    private final Map<Integer, Task> tasks = new HashMap<>();

    private int idCounter = 1;

    public TodoRepository() {
        loadFromFile();
    }

    public void createTask(Task task) {
        task.setId(idCounter);
        tasks.put(task.getId(), task);
        idCounter++;
        saveToFile();
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Collection<Task> getAllTask() {
        return tasks.values();
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
        saveToFile();
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
        saveToFile();
    }

    private void saveToFile() {
        List<String> lines = new ArrayList<>();

        for (Task task : tasks.values()) {
            lines.add(task.getId() + "|" + task.getName() + "|" + task.getDescription() + "|" + task.isCompleted());
        }

        try {
            Files.write(Paths.get("tasks.txt"), lines);
        } catch (IOException e) {
            System.out.println("[Ошибка] Ошибка при создании файла!");
        }
    }

    private void loadFromFile() {
        Path path = Paths.get("tasks.txt");
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);

                int maxId = 0;
                for (String line : lines) {
                    String[] parts = line.split("\\|");

                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String description = parts[2];
                    boolean isCompleted = Boolean.parseBoolean(parts[3]);

                    Task task = new Task(name, description);
                    task.setId(id);
                    task.setCompleted(isCompleted);

                    tasks.put(task.getId(), task);

                    if(id > maxId) {
                        maxId = id;
                    }

                    this.idCounter = maxId + 1;
                }
            } catch (IOException e) {
                System.out.println("[Ошибка] при чтении файла: " + e.getMessage());
            }
        }
    }
}
