package ru.danilscheglov;

/**
 * @author Danil Scheglov
 */
public class TodoApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext();
        context.getTodoController().start();
    }
}