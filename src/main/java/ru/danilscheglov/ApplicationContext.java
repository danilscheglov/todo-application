package ru.danilscheglov;

import ru.danilscheglov.controller.TodoController;
import ru.danilscheglov.repository.TodoRepository;
import ru.danilscheglov.service.TodoService;

/**
 * @author Danil Scheglov
 */
public class ApplicationContext {

    private final TodoController todoController;

    public ApplicationContext() {
        TodoRepository todoRepository = new TodoRepository();
        TodoService todoService = new TodoService(todoRepository);
        this.todoController = new TodoController(todoService);
    }

    public TodoController getTodoController() {
        return todoController;
    }
}
