package ello.ui;

import static ello.common.util.AppConstants.APP_LOGO;

import java.io.IOException;

import ello.Ello;
import ello.common.util.AppConstants;
import ello.logic.Logic;
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

            stage.getIcons().add(APP_LOGO);
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
}
