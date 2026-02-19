package ello.ui;

import java.io.IOException;

import ello.ui.util.CssStyleConstants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;

/**
 * Represents a dialog box in the GUI. This is an abstract class that serves as a base for specific dialog box types.
 */
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getFxmlPath()));
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
    protected abstract String getFxmlPath();

    /**
     * Changes the dialog CSS style based on the command type. Each command type corresponds to a
     * specific CSS class that can be defined in the application's stylesheet.
     *
     * @param commandType The type of command to determine the style to apply.
     */
    protected void changeDialogStyle(String commandType) {
        assert commandType != null : "Command type should not be null";

        switch (commandType) {
        case "add_todo":
            dialog.getStyleClass().add(CssStyleConstants.ADD_COMMAND_TODO);
            break;
        case "add_deadline":
            dialog.getStyleClass().add(CssStyleConstants.ADD_COMMAND_DEADLINE);
            break;
        case "add_event":
            dialog.getStyleClass().add(CssStyleConstants.ADD_COMMAND_EVENT);
            break;
        case "change_mark":
            dialog.getStyleClass().add(CssStyleConstants.CHANGE_MARK_COMMAND);
            break;
        case "delete":
            dialog.getStyleClass().add(CssStyleConstants.DELETE_COMMAND);
            break;
        case "error":
            dialog.getStyleClass().add(CssStyleConstants.ERROR_COMMAND);
            dialogTail.getStyleClass().add(CssStyleConstants.ERROR_TAIL);
            break;
        case "list":
            dialog.getStyleClass().add(CssStyleConstants.LIST_COMMAND);
            break;
        case "find":
            dialog.getStyleClass().add(CssStyleConstants.FIND_COMMAND);
            break;
        case "system":
            dialog.getStyleClass().add(CssStyleConstants.SYSTEM_MESSAGE);
            break;
        case "help":
            dialog.getStyleClass().add(CssStyleConstants.HELP_MESSAGE);
            dialogTail.getStyleClass().add(CssStyleConstants.HELP_TAIL);
            break;
        default:
            // Do nothing, use default style
        }
    }
}


