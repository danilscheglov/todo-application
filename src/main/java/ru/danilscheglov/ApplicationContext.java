package ru.danilscheglov;

import ru.danilscheglov.controller.TodoController;
import ru.danilscheglov.repository.TodoRepository;

/**
 * @author Danil Scheglov
 */
public class ApplicationContext {

    private final TodoController todoController;

    public ApplicationContext() {
        TodoRepository todoRepository = new TodoRepository();
        this.todoController = new TodoController(todoRepository);
    }

    public TodoController getTodoController() {
        return todoController;
    }
}
