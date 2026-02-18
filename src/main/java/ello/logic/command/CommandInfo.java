package ello.logic.command;

/**
 * Represents information about a command including its name, description, and syntax.
 * Used by the help system through {@link HelpCommand} to display command documentation.
 */
public record CommandInfo(String commandName, String description, String syntax) {
    /**
     * Creates a CommandInfo with the specified details.
     *
     * @param commandName The simple name of the command (e.g., "exit", "mark", "todo").
     * @param description A brief description of what the command does.
     * @param syntax      The detailed syntax of how to use the command.
     */
    public CommandInfo {
    }

    /**
     * Gets the command name.
     *
     * @return The command name.
     */
    @Override
    public String commandName() {
        return commandName;
    }

    /**
     * Gets the command description.
     *
     * @return The command description.
     */
    @Override
    public String description() {
        return description;
    }

    /**
     * Gets the command syntax.
     *
     * @return The command syntax.
     */
    @Override
    public String syntax() {
        return syntax;
    }
}
