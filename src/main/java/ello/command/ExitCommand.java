package ello.command;

import ello.storage.TaskList;
import ello.ui.Ui;

/**
 * Represents a {@link Command} to exit the application.
 */
public class ExitCommand implements Command {
    /**
     * Executes exit by displaying a goodbye message.
     *
     * @param taskList The {@link TaskList} to operate on.
     * @param ui       The {@link Ui} for user interaction.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}