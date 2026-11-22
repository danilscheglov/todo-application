package ru.danilscheglov.todo.model;

import java.time.LocalDateTime;

public record Task(Long id, String title, boolean isCompleted, LocalDateTime createdAt) {

    public static Task createNew(String title) {
        return new Task(null, title, false, null);
    }

    public Task withCompleted(boolean completed) {
        return new Task(id, title, completed, createdAt);
    }
}
