package ello.ui;

import javafx.scene.image.Image;

/**
 * Represents a dialog box for user messages.
 * The image is on the right, message bubble on the left of the image.
 */
public class UserDialogBox extends DialogBox {

    /**
     * Creates a new UserDialogBox with the given text and image.
     *
     * @param text The message text.
     * @param img  The user's profile image.
     */
    public UserDialogBox(String text, Image img) {
        super(text, img);
    }

    /**
     * Factory method to create a UserDialogBox.
     *
     * @param text The message text.
     * @param img  The user's profile image.
     * @return A new UserDialogBox instance.
     */
    public static UserDialogBox create(String text, Image img) {
        return new UserDialogBox(text, img);
    }

    @Override
    protected String getFXMLPath() {
        return "/view/UserDialogBox.fxml";
    }
}
