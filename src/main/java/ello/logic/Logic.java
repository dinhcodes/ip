package ello.logic;

import ello.common.exception.ElloException;
import ello.logic.command.Command;
import ello.logic.command.CommandResult;
import ello.logic.command.exception.InvalidCommandException;
import ello.logic.command.parser.CommandParser;
import ello.storage.TaskStorage;

/**
 * The main logic manager of the application.
 * Handles command parsing and execution.
 */
public class Logic {
    private final TaskStorage taskStorage;

    /**
     * Constructs a Logic instance with the specified task storage.
     *
     * @param taskStorage The storage to use for task persistence.
     */
    public Logic(TaskStorage taskStorage) {
        this.taskStorage = taskStorage;
    }

    /**
     * Executes the command and returns the result for display.
     *
     * @param commandText The command text entered by the user.
     * @return The result of the command execution.
     */
    public CommandResult execute(String commandText) {
        try {
            Command command = CommandParser.parse(commandText);
            CommandResult result = command.execute(taskStorage.getTaskList());
            taskStorage.save();
            return result;
        } catch (ElloException e) {
            return new CommandResult(e.getMessage(), "error");
        } catch (InvalidCommandException e) {
            return new CommandResult(e.getMessage(), "error");
        } catch (Exception e) {
            return new CommandResult("An unexpected error occurred: " + e.getMessage(), "error");
        }
    }

    /**
     * Gets the status message from loading tasks.
     *
     * @return The load status message.
     */
    public String getLoadStatusMessage() {
        return taskStorage.loadAndGetStatusMessage();
    }
}
