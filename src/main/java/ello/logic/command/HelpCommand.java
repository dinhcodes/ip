package ello.logic.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ello.logic.command.exception.InvalidCommandException;
import ello.model.task.TaskList;

/**
 * Represents a command to display help information about available commands.
 */
public class HelpCommand extends Command {
    private static final String SYSTEM_MESSAGE_TYPE = "help";
    private static final CommandInfo COMMAND_INFO = new CommandInfo(
            "help",
            "Shows help information",
            "Syntax: help | help <command_number>"
    );

    private final Integer commandIndex;

    /**
     * Creates a HelpCommand that displays all available commands.
     */
    public HelpCommand() {
        super("help");
        this.commandIndex = null;
    }

    /**
     * Creates a HelpCommand that displays detailed help for a specific command.
     *
     * @param commandIndex The 1-based index of the command to show detailed help for.
     */
    public HelpCommand(int commandIndex) {
        super("help");
        this.commandIndex = commandIndex;
    }

    private static List<CommandInfo> getAllCommandInfos() {
        List<CommandInfo> allInfos = new ArrayList<>();

        allInfos.addAll(AddTaskCommand.COMMAND_INFO_LIST);
        allInfos.add(ListCommand.COMMAND_INFO);
        allInfos.addAll(MarkCommand.COMMAND_INFO_LIST);
        allInfos.add(DeleteCommand.COMMAND_INFO);
        allInfos.add(FindCommand.COMMAND_INFO);
        allInfos.add(ExitCommand.COMMAND_INFO);

        return allInfos;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        if (commandIndex == null) {
            return new CommandResult(listAllCommands(), SYSTEM_MESSAGE_TYPE);
        }
        return new CommandResult(getDetailedHelp(), SYSTEM_MESSAGE_TYPE);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public List<CommandInfo> getCommandInfoList() {
        return Collections.singletonList(COMMAND_INFO);
    }

    private String listAllCommands() {
        List<CommandInfo> allCommandInfos = getAllCommandInfos();
        return buildCommandList(allCommandInfos);
    }

    private String buildCommandList(List<CommandInfo> commandInfos) {
        StringBuilder sb = new StringBuilder("Available commands:\n\n");

        for (int i = 0; i < commandInfos.size(); i++) {
            CommandInfo info = commandInfos.get(i);
            sb.append(i + 1).append(". ")
                    .append(info.commandName()).append(": ")
                    .append(info.description()).append("\n");
        }
        sb.append("\n\n");
        sb.append("For more info, type 'help <number>' to see "
                + "detailed syntax for each command.");

        return sb.toString().trim();
    }

    private String getDetailedHelp() {
        List<CommandInfo> allCommandInfos = getAllCommandInfos();
        return buildDetailedHelp(allCommandInfos);
    }

    private String buildDetailedHelp(List<CommandInfo> commandInfos) {
        if (commandIndex < 1 || commandIndex > commandInfos.size()) {
            throw new InvalidCommandException("Invalid command number " + commandIndex + ". "
                    + "Please provide a number between 1 and " + commandInfos.size() + ".");
        }

        CommandInfo info = commandInfos.get(commandIndex - 1);
        return info.commandName() + ": " + info.description() + "\n\n" + info.syntax();
    }
}
