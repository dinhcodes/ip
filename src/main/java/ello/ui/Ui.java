package ello.ui;

import static ello.common.util.AppConstants.appLogo;

import java.io.IOException;

import ello.Ello;
import ello.common.util.AppConstants;
import ello.logic.Logic;
import ello.ui.util.UiConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the User Interface (UI) component of the Ello application.
 * Responsible for handling message display for GUI interactions.
 */
public class Ui {

    /**
     * Starts the UI by loading the main window and setting up the stage.
     *
     * @param stage The primary stage for this application.
     * @param logic The logic component to handle command execution.
     */
    public void startUi(Stage stage, Logic logic) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ello.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            stage.getIcons().add(appLogo);
            stage.setTitle(AppConstants.NAME);

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(417);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setLogic(logic);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays a message to the user, wrapped with lines for better readability.
     *
     * @param message Message to be displayed
     */
    public void showMessage(String message) {
        // Default implementation - can be overridden for capturing output
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        showMessage(UiConstants.GOODBYE_MESSAGE);
    }

    /**
     * Generates a message indicating the current count of tasks in the list.
     *
     * @param count The number of tasks.
     * @return A String message indicating the task count.
     */
    public String countTasks(int count) {
        assert (count >= 0) : "Task count should not be negative.";
        if (count == 1) {
            return "Now you have 1 Task in the list.";
        }
        return "Now you have " + count + " tasks in the list.";
    }

    /**
     * Shows an error message to the user.
     *
     * @param errorMessage The error message to display
     */
    public void showError(String errorMessage) {
        showMessage(errorMessage);
    }
}
