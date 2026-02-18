package ello.command.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import ello.logic.command.exception.InvalidCommandException;
import ello.logic.command.exception.MarkersOutOfOrderException;
import ello.logic.command.exception.MissingMarkerDescException;
import ello.logic.command.exception.MissingMarkerException;
import ello.logic.command.exception.MissingSpaceAfterAddTaskCommandException;
import ello.logic.command.exception.MissingTaskDescException;
import ello.logic.command.parser.AddTaskCommandParser;
import ello.model.task.Task;
import ello.model.task.impl.Deadline;
import ello.model.task.impl.Event;
import ello.model.task.impl.Todo;

class AddTaskCommandParserTest {
    @Test
    void processTask_Command_validTodo_returnsTodo() {
        Task task = assertDoesNotThrow(() -> AddTaskCommandParser.validateParseAndCreateTask("todo buy milk"));
        assertNotNull(task);
        Todo expected = new Todo("buy milk");
        assertEquals(expected, task);
    }

    @Test
    void processTask_Command_validDeadline_returnsDeadline() {
        Task task = assertDoesNotThrow(() ->
                AddTaskCommandParser.validateParseAndCreateTask("deadline submit report /by 2/12/2019 18:00"));
        assertNotNull(task);
        Deadline expected = new Deadline("submit report", LocalDateTime.of(2019, 12, 2, 18, 0));
        assertEquals(expected, task);
    }

    @Test
    void processTask_Command_validEvent_returnsEvent() {
        Task task = assertDoesNotThrow(() ->
                AddTaskCommandParser.validateParseAndCreateTask("event project meeting /from 2/12/2019 18:00 /to 2/12/2019 20:00"));
        assertNotNull(task);
        Event expected = new Event("project meeting",
                LocalDateTime.of(2019, 12, 2, 18, 0),
                LocalDateTime.of(2019, 12, 2, 20, 0));
        assertEquals(expected, task);
    }

    @Test
    void processTask_missingDescription_throwsMissingTaskCommandDesc() {
        assertThrows(MissingTaskDescException.class,
                () -> AddTaskCommandParser.validateParseAndCreateTask("todo"));
    }

    @Test
    void processTask_Command_missingSpaceAfterCommandWord_throwsMissingSpace() {
        assertThrows(MissingSpaceAfterAddTaskCommandException.class,
                () -> AddTaskCommandParser.validateParseAndCreateTask("todoBuy milk"));
    }

    @Test
    void processTask_Command_invalidCommand_throwsInvalidCommand() {
        assertThrows(InvalidCommandException.class,
                () -> AddTaskCommandParser.validateParseAndCreateTask("unknown task"));
    }

    @Test
    void processTask_Command_missingMarker_throwsMissingMarker() {
        assertThrows(MissingMarkerException.class,
                () -> AddTaskCommandParser.validateParseAndCreateTask("deadline submit report"));
    }

    @Test
    void processTask_Command_missingMarkerDesc_throwsMissingMarkerDesc() {
        assertThrows(MissingMarkerDescException.class,
                () -> AddTaskCommandParser.validateParseAndCreateTask("deadline submit report /by "));
    }

    @Test
    void processTask_Command_markersOutOfOrder_throwsMarkersOutOfOrder() {
        assertThrows(MarkersOutOfOrderException.class,
                () -> AddTaskCommandParser.validateParseAndCreateTask("event meetup /to6pm /from5pm"));
    }

    @Test
    void processTask_Command_eventMissingMarkerDesc_throwsMissingMarkerDesc() {
        assertThrows(MissingMarkerDescException.class,
                () -> AddTaskCommandParser.validateParseAndCreateTask("event meetup /from5pm /to "));
    }
}