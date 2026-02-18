package ello.ui;

import ello.logic.Logic;
import ello.logic.command.CommandResult;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/pics/user.png"));
    private final Image elloImage = new Image(this.getClass().getResourceAsStream("/images/pics/ello.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Logic logic;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Logic instance.
     *
     * @param logic The logic component to handle command execution.
     */
    public void setLogic(Logic logic) {
        this.logic = logic;
        loadTasksOnGui();
    }

    /**
     * Displays the welcome message with the load status.
     */
    private void loadTasksOnGui() {
        String loadStatus = logic.getLoadStatusMessage();
        ElloDialogBox welcomeBox = ElloDialogBox.create(loadStatus, elloImage);
        dialogContainer.getChildren().add(welcomeBox);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ello's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        // Validate input
        String input = userInput.getText();

        // Process input and get response
        CommandResult result = logic.execute(input);
        ElloDialogBox elloDialogBox = ElloDialogBox.create(result.getFeedbackToUser(), elloImage);
        elloDialogBox.setCommandStyle(result.getTypeOfCommand());

        // Display user input and Ello's response
        dialogContainer.getChildren().addAll(
                UserDialogBox.create(input, userImage),
                elloDialogBox
        );

        userInput.clear();

        // Exit application if the command is an exit command
        if (result.shouldExit()) {
            Platform.exit();
        }
    }
}
