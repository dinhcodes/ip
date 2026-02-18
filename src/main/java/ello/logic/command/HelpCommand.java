package ello.logic.command;

import ello.model.task.TaskList;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of available commands and their usage.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        String helpMessage = "Available commands:\n"
                + "1. add todo <description> - Adds a new todo task.\n"
                + "2. add deadline <description> /by <date> - Adds a new deadline task.\n"
                + "3. add event <description> /at <date> - Adds a new event task.\n"
                + "4. list - Lists all tasks.\n"
                + "5. done <task number> - Marks a task as done.\n"
                + "6. delete <task number> - Deletes a task.\n"
                + "7. find <keyword> - Finds tasks containing the keyword.\n"
                + "8. help - Shows this help message.";
        return new CommandResult(helpMessage);
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
