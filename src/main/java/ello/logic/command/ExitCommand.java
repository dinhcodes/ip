package ello.logic.command;

import java.util.Collections;
import java.util.List;

import ello.model.task.TaskList;

/**
 * Represents a {@link Command} to exit the application.
 */
public class ExitCommand extends Command {
    public static final CommandInfo COMMAND_INFO = new CommandInfo(
            "bye",
            "Exits the application",
            "Syntax: bye"
    );

    /**
     * Creates an ExitCommand.
     */
    public ExitCommand() {
        super("exit");
    }

    /**
     * Executes the ExitCommand by creating a goodbye message result.
     *
     * @param taskList The task list to operate on.
     * @return The command result containing the execution result
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        return new CommandResult(goodbyeMessage, true, getCommandType());
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public List<CommandInfo> getCommandInfoList() {
        return Collections.singletonList(COMMAND_INFO);
    }
}
