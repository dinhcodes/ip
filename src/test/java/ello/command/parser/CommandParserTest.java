package ello.command.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ello.logic.command.DeleteCommand;
import ello.logic.command.ExitCommand;
import ello.logic.command.FindCommand;
import ello.logic.command.ListCommand;
import ello.logic.command.MarkCommand;
import ello.logic.command.exception.EmptyCommandException;
import ello.logic.command.exception.InvalidCommandException;
import ello.logic.command.parser.CommandParser;

class CommandParserTest {
    @Test
    void parse_bye_returnsExitCommand() {
        assertInstanceOf(ExitCommand.class, CommandParser.parse("bye"));
    }

    @Test
    void parse_list_returnsListCommand() {
        assertInstanceOf(ListCommand.class, CommandParser.parse("list"));
    }

    @Test
    void parse_find_returnsFindCommand() {
        assertInstanceOf(FindCommand.class, CommandParser.parse("find book"));
        assertInstanceOf(FindCommand.class, CommandParser.parse("find meeting"));
    }

    @Test
    void parse_mark_returnsMarkCommand() {
        assertInstanceOf(MarkCommand.class, CommandParser.parse("mark 2"));
    }

    @Test
    void parse_unmark_returnsMarkCommand() {
        assertInstanceOf(MarkCommand.class, CommandParser.parse("unmark 5"));
    }

    @Test
    void parse_delete_returnsDeleteCommand() {
        assertInstanceOf(DeleteCommand.class, CommandParser.parse("delete 3"));
    }

    @Test
    void parse_empty_throwsEmptyCommandException() {
        assertThrows(EmptyCommandException.class, () -> CommandParser.parse(" "));
    }

    @Test
    void parse_invalid_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> CommandParser.parse("unknown"));
    }
}
