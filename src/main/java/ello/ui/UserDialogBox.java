package ello.ui;

import javafx.scene.image.Image;

/**
 * Represents a dialog box for user messages.
 * The image is on the right, message bubble on the left of the image.
 */
public class UserDialogBox extends DialogBox {

    /**
     * Creates a new UserDialogBox with the given text and image.
     * Optionally accepts additional CSS style classes.
     *
     * @param text         The message text.
     * @param img          User's profile image.
     * @param styleClasses Additional CSS style classes to apply.
     */
    public UserDialogBox(String text, Image img, String... styleClasses) {
        super(text, img, styleClasses);
    }

    /**
     * Factory method to create a UserDialogBox.
     *
     * @param text         The message text.
     * @param img          User's profile image.
     * @param styleClasses Additional CSS style classes to apply.
     * @return A new UserDialogBox instance.
     */
    public static UserDialogBox create(String text, Image img, String... styleClasses) {
        return new UserDialogBox(text, img, styleClasses);
    }

    @Override
    protected String getFxmlPath() {
        return "/view/UserDialogBox.fxml";
    }
}
