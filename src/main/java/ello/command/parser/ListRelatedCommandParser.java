package ello.command.parser;

import ello.command.Command;
import ello.command.CommandType;
import ello.command.exception.InvalidCommandException;
import ello.command.exception.MissingSpaceAfterCommandWordException;

/**
 * Utility class that parses list-related commands (find, mark, unmark, delete).
 * Similar to {@link TaskParser}, this parser validates command syntax and creates appropriate command objects.
 */
public class ListRelatedCommandParser {
    /**
     * Validates, parses and creates a list-related command from the given input.
     *
     * @param command The input command from the user.
     * @return The created command.
     */
    public static Command processListRelatedCommand(String command) {
        CommandType type = extractCommandType(command);
        String argument = extractArgument(command, type);
        return type.getCommandCreator().create(argument);
    }

    /**
     * Extracts the command type from the command string.
     *
     * @param command The input command.
     * @return The matching command type.
     * @throws MissingSpaceAfterCommandWordException If there's no space after the command word when required.
     * @throws InvalidCommandException               If no matching command type is found.
     */
    private static CommandType extractCommandType(String command) {
        for (CommandType type : CommandType.values()) {
            // Check for exact match with space
            if (command.startsWith(type.getCommandWord() + " ")) {
                return type;
            }
            // Check if command equals the command word exactly
            if (command.equals(type.getCommandWord())) {
                return type;
            }
            // Check for missing space after command word
            if (command.startsWith(type.getCommandWord())
                    && command.length() > type.getCommandWord().length()
                    && type.requiresArgument()) {
                throw new MissingSpaceAfterCommandWordException(type);
            }
        }
        throw new InvalidCommandException();
    }

    /**
     * Extracts the argument portion from the command string.
     *
     * @param command The input command.
     * @param type    The command type.
     * @return The argument string.
     */
    private static String extractArgument(String command, CommandType type) {
        int startIndex = type.getCommandWord().length();
        return command.substring(startIndex).trim();
    }
}
