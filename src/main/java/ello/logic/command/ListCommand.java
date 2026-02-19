package ello.logic.command;

import java.util.Collections;
import java.util.List;

import ello.model.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    public static final CommandInfo COMMAND_INFO = new CommandInfo(
            "list",
            "Lists all tasks",
            "Syntax: list"
    );

    /**
     * Creates a ListCommand.
     */
    public ListCommand() {
        super("list");
    }

    /**
     * Executes the list command by creating a message with all tasks in the task list.
     *
     * @param taskList The task list to operate on.
     * @return The command result containing the execution result.
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        String feedback;
        if (taskList.size() == 0) {
            feedback = "Your task list is empty.";
        } else {
            feedback = "Here are the tasks in your list:\n\n" + taskList;
        }

        return new CommandResult(feedback, getCommandType());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public List<CommandInfo> getCommandInfoList() {
        return Collections.singletonList(COMMAND_INFO);
    }
}
