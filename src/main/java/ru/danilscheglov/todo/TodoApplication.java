package ru.danilscheglov.todo;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.danilscheglov.todo.ui.TodoMainView;
import ru.danilscheglov.todo.util.FxUtils;

import static ru.danilscheglov.todo.util.Constants.*;

public class TodoApplication extends Application {

    @Override
    public void start(Stage stage) {
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());

        TodoMainView mainView = new TodoMainView();

        Scene scene = new Scene(mainView.getView(), WINDOW_INITIAL_WIDTH, WINDOW_INITIAL_HEIGHT);

        stage.setTitle(APP_TITLE);
        stage.setMinWidth(WINDOW_MIN_WIDTH);
        stage.setMinHeight(WINDOW_MIN_HEIGHT);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();

        Platform.runLater(() -> FxUtils.setStageIcon(stage, APP_ICON_PATH));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
