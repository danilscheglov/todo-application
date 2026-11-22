package ru.danilscheglov.todo.util;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;

public final class FxUtils {

    private FxUtils() {
    }

    public static void applyStylesheet(Parent root, String stylesheetPath) {
        URL stylesheetUrl = FxUtils.class.getResource(stylesheetPath);
        if (stylesheetUrl == null) {
            System.err.println("Не удалось найти файл стилей: " + stylesheetPath);
            return;
        }
        root.getStylesheets().add(stylesheetUrl.toExternalForm());
    }

    public static void setStageIcon(Stage stage, String iconPath) {
        try (InputStream iconStream = FxUtils.class.getResourceAsStream(iconPath)) {
            if (iconStream == null) {
                System.err.println("Не удалось найти файл иконки: " + iconPath);
                return;
            }
            stage.getIcons().setAll(new Image(iconStream));
        } catch (Exception e) {
            System.err.println("Ошибка при загрузке иконки (" + iconPath + "): " + e.getMessage());
        }
    }
}
