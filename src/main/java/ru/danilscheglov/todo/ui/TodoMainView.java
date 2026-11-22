package ru.danilscheglov.todo.ui;

import atlantafx.base.theme.Styles;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ru.danilscheglov.todo.model.Task;
import ru.danilscheglov.todo.util.FxUtils;

import static javafx.collections.FXCollections.observableArrayList;
import static ru.danilscheglov.todo.util.Constants.*;

public class TodoMainView {

    private final ObservableList<Task> tasks = observableArrayList();
    private final BorderPane root = new BorderPane();
    private final TextField titleInput = new TextField();

    public TodoMainView() {
        initLayout();
    }

    public Parent getView() {
        return root;
    }

    private void initLayout() {
        FxUtils.applyStylesheet(root, STYLESHEET_PATH);
        root.setPadding(new Insets(20));

        Label headerLabel = new Label(HEADER_TITLE);
        headerLabel.getStyleClass().add(Styles.TITLE_2);

        titleInput.setPromptText(INPUT_PROMPT_TEXT);
        HBox.setHgrow(titleInput, Priority.ALWAYS);

        Button addButton = new Button(ADD_BUTTON_TEXT);
        addButton.getStyleClass().add(Styles.ACCENT);

        HBox inputBox = new HBox(10, titleInput, addButton);
        inputBox.setAlignment(Pos.CENTER_LEFT);

        VBox topBox = new VBox(14, headerLabel, inputBox);
        topBox.setPadding(new Insets(0, 0, 16, 0));
        root.setTop(topBox);

        ListView<Task> taskListView = new ListView<>(tasks);
        taskListView.getStyleClass().add(Styles.STRIPED);
        taskListView.setPlaceholder(new Label(EMPTY_LIST_PLACEHOLDER));
        root.setCenter(taskListView);
    }
}
