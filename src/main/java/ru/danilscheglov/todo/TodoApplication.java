package ru.danilscheglov.todo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static ru.danilscheglov.todo.ui.UiConstants.*;

public class TodoApplication extends Application {

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, WINDOW_INITIAL_WIDTH, WINDOW_INITIAL_HEIGHT);

        stage.setTitle(APP_TITLE);
        stage.setMinWidth(WINDOW_MIN_WIDTH);
        stage.setMinHeight(WINDOW_MIN_HEIGHT);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}