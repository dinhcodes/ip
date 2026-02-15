package ello.command.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ello.command.DeleteCommand;
import ello.command.ExitCommand;
import ello.command.FindCommand;
import ello.command.ListCommand;
import ello.command.MarkCommand;
import ello.command.exception.EmptyCommandException;
import ello.command.exception.InvalidCommandException;
import ello.command.exception.MissingSpaceAfterCommandWordException;

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

    @Test
    void parse_findNoSpace_throwsMissingSpaceAfterCommandWordException() {
        assertThrows(MissingSpaceAfterCommandWordException.class, () -> CommandParser.parse("findbook"));
    }

    @Test
    void parse_markNoSpace_throwsMissingSpaceAfterCommandWordException() {
        assertThrows(MissingSpaceAfterCommandWordException.class, () -> CommandParser.parse("mark2"));
    }

    @Test
    void parse_deleteNoSpace_throwsMissingSpaceAfterCommandWordException() {
        assertThrows(MissingSpaceAfterCommandWordException.class, () -> CommandParser.parse("delete3"));
    }
}
