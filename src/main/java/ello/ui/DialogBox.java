package ello.ui;

import java.io.IOException;

import ello.ui.util.StyleConstants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;

public abstract class DialogBox extends HBox {

    @FXML
    protected Label dialog;
    @FXML
    protected ImageView displayPicture;
    @FXML
    protected SVGPath dialogTail;

    /**
     * Constructor for DialogBox. Loads the FXML and sets text and image.
     * Optionally accepts additional CSS style classes via varargs.
     *
     * @param text         The text to display in the dialog.
     * @param img          The image to display.
     * @param styleClasses Additional CSS style classes to apply to the dialog label.
     */
    protected DialogBox(String text, Image img, String... styleClasses) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getFXMLPath()));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        // Apply additional style classes if provided
        if (styleClasses.length > 0) {
            dialog.getStyleClass().addAll(styleClasses);
        }
    }

    /**
     * Abstract method to get the FXML path for each subclass.
     */
    protected abstract String getFXMLPath();

    /**
     * Changes the dialog style based on the command type. Each command type corresponds to a
     * specific CSS class that can be defined in the application's stylesheet.
     *
     * @param commandType The type of command to determine the style to apply.
     */
    protected void changeDialogStyle(String commandType) {
        assert commandType != null : "Command type should not be null";

        switch (commandType) {
        case "AddCommand_todo":
            dialog.getStyleClass().add(StyleConstants.ADD_COMMAND_TODO);
            break;
        case "AddCommand_deadline":
            dialog.getStyleClass().add(StyleConstants.ADD_COMMAND_DEADLINE);
            break;
        case "AddCommand_event":
            dialog.getStyleClass().add(StyleConstants.ADD_COMMAND_EVENT);
            break;
        case "ChangeMarkCommand":
            dialog.getStyleClass().add(StyleConstants.CHANGE_MARK_COMMAND);
            break;
        case "DeleteCommand":
            dialog.getStyleClass().add(StyleConstants.DELETE_COMMAND);
            break;
        case "ErrorCommand":
            dialog.getStyleClass().add(StyleConstants.ERROR_COMMAND);
            dialogTail.getStyleClass().add(StyleConstants.ERROR_TAIL);
            break;
        case "ListCommand":
            dialog.getStyleClass().add(StyleConstants.LIST_COMMAND);
            break;
        case "FindCommand":
            dialog.getStyleClass().add(StyleConstants.FIND_COMMAND);
            break;
        case "SystemMessage":
            dialog.getStyleClass().add(StyleConstants.SYSTEM_MESSAGE);
            break;
        default:
            // Do nothing
        }
    }
}


