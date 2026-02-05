package ello.command.parser;

import ello.command.DeleteCommand;
import ello.command.ExitCommand;
import ello.command.ListCommand;
import ello.command.MarkCommand;
import ello.command.exception.EmptyCommandException;
import ello.command.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void parse_mark_returnsMarkCommand() {
        assertInstanceOf(MarkCommand.class, CommandParser.parse("mark2"));
    }

    @Test
    void parse_unmark_returnsMarkCommand() {
        assertInstanceOf(MarkCommand.class, CommandParser.parse("unmark5"));
    }

    @Test
    void parse_delete_returnsDeleteCommand() {
        assertInstanceOf(DeleteCommand.class, CommandParser.parse("delete3"));
    }

    @Test
    void parse_empty_throwsEmptyCommandException() {
        assertThrows(EmptyCommandException.class, () -> CommandParser.parse(" "));
    }

    @Test
    void parse_invalid_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> CommandParser.parse("unknown"));
    }

    @Test
    void extractTaskIndex_valid_returnsZeroBased() {
        assertEquals(1, CommandParser.extractTaskIndex("mark2", "mark"));
        assertEquals(4, CommandParser.extractTaskIndex("delete5", "delete"));
    }

    @Test
    void extractTaskIndex_invalidNumber_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class,
                () -> CommandParser.extractTaskIndex("delete abc", "delete"));
    }
}
