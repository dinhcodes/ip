package ello.ui;

import javafx.scene.image.Image;

/**
 * Represents a dialog box for Ello's messages.
 */
public class ElloDialogBox extends DialogBox {

    /**
     * Creates a new ElloDialogBox with the given text and image.
     *
     * @param text The message text.
     * @param img  Ello's profile image.
     */
    public ElloDialogBox(String text, Image img) {
        super(text, img);
    }

    /**
     * Factory method to create an ElloDialogBox.
     *
     * @param text The message text.
     * @param img  Ello's profile image.
     * @return A new ElloDialogBox instance.
     */
    public static ElloDialogBox create(String text, Image img) {
        return new ElloDialogBox(text, img);
    }

    @Override
    protected String getFXMLPath() {
        return "/view/ElloDialogBox.fxml";
    }

    /**
     * Changes the style of the dialog box based on the command type.
     *
     * @param commandType The type of command.
     */
    public void setCommandStyle(String commandType) {
        changeDialogStyle(commandType);
    }
}
