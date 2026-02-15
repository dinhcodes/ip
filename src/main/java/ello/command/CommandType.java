package ello.command;

/**
 * Enumeration representing different types of list-related commands.
 * These commands operate on the task list (e.g., finding, marking, deleting tasks).
 */
public enum CommandType {
    FIND("find", true, FindCommand::new),
    MARK("mark", true, arg -> {
        int index = parseIndex(arg);
        return new MarkCommand(index, true);
    }),
    UNMARK("unmark", true, arg -> {
        int index = parseIndex(arg);
        return new MarkCommand(index, false);
    }),
    DELETE("delete", true, arg -> {
        int index = parseIndex(arg);
        return new DeleteCommand(index);
    });

    private final String commandWord;
    private final boolean requiresArgument;
    private final CommandCreator commandCreator;

    /**
     * Constructor for command type enum.
     *
     * @param commandWord      The command word that triggers this command.
     * @param requiresArgument Whether this command requires an argument after the command word.
     * @param commandCreator   The function to create commands of this type.
     */
    CommandType(String commandWord, boolean requiresArgument, CommandCreator commandCreator) {
        this.commandWord = commandWord;
        this.requiresArgument = requiresArgument;
        this.commandCreator = commandCreator;
    }

    /**
     * Helper method to parse a task index from a string.
     *
     * @param indexStr The index string to parse.
     * @return The zero-based task index.
     * @throws ello.command.exception.InvalidCommandException If the index is not a valid integer.
     */
    private static int parseIndex(String indexStr) {
        try {
            return Integer.parseInt(indexStr) - 1; // Convert to zero-based index
        } catch (NumberFormatException e) {
            throw new ello.command.exception.InvalidCommandException(
                    "Invalid index: " + indexStr + ". Please provide a valid number.");
        }
    }

    /**
     * Finds a command type by its command word.
     *
     * @param commandWord The command word to search for.
     * @return The matching command type, or {@code null} if no match is found.
     */
    public static CommandType fromCommandWord(String commandWord) {
        for (CommandType type : values()) {
            if (type.commandWord.equals(commandWord)) {
                return type;
            }
        }
        return null;
    }

    /**
     * Checks if a command string starts with any known command word.
     *
     * @param command The command string to check.
     * @return {@code true} if the command starts with a known command word, {@code false} otherwise.
     */
    public static boolean isAListRelatedCommand(String command) {
        for (CommandType type : values()) {
            if (command.startsWith(type.commandWord)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the command word associated with this command type.
     *
     * @return The command word.
     */
    public String getCommandWord() {
        return commandWord;
    }

    /**
     * Gets the command creator associated with this command type.
     *
     * @return The command creator function.
     */
    public CommandCreator getCommandCreator() {
        return commandCreator;
    }

    /**
     * Checks if this command type requires an argument.
     *
     * @return {@code true} if an argument is required, {@code false} otherwise.
     */
    public boolean requiresArgument() {
        return requiresArgument;
    }

    /**
     * Builds the syntax string for this command.
     *
     * @return The syntax string.
     */
    public String buildSyntax() {
        return "Syntax: " + commandWord + (requiresArgument ? " <argument>" : "");
    }

    @Override
    public String toString() {
        return commandWord;
    }
}
