package ello.command.parser;

import ello.command.exception.InvalidCommandException;
import ello.command.exception.MarkersOutOfOrderException;
import ello.command.exception.MissingMarkerDescException;
import ello.command.exception.MissingMarkerException;
import ello.command.exception.MissingSpaceAfterCommandWordException;
import ello.command.exception.MissingTaskDescException;
import ello.task.Task;
import ello.task.impl.Deadline;
import ello.task.impl.Event;
import ello.task.impl.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskParserTest {
    @Test
    void validateParseAndCreateTask_validTodo_returnsTodo() {
        Task task = assertDoesNotThrow(() -> TaskParser.validateParseAndCreateTask("todo buy milk"));
        assertNotNull(task);
        Todo expected = new Todo("buy milk");
        assertEquals(expected, task);
    }

    @Test
    void validateParseAndCreateTask_validDeadline_returnsDeadline() {
        Task task = assertDoesNotThrow(() ->
                TaskParser.validateParseAndCreateTask("deadline submit report /by 2/12/2019 18:00"));
        assertNotNull(task);
        Deadline expected = new Deadline("submit report", LocalDateTime.of(2019, 12, 2, 18, 0));
        assertEquals(expected, task);
    }

    @Test
    void validateParseAndCreateTask_validEvent_returnsEvent() {
        Task task = assertDoesNotThrow(() ->
                TaskParser.validateParseAndCreateTask("event project meeting /from 2/12/2019 18:00 /to 2/12/2019 20:00"));
        assertNotNull(task);
        Event expected = new Event("project meeting",
                LocalDateTime.of(2019, 12, 2, 18, 0),
                LocalDateTime.of(2019, 12, 2, 20, 0));
        assertEquals(expected, task);
    }

    @Test
    void validateParseAndCreateTask_missingDescription_throwsMissingTaskDesc() {
        assertThrows(MissingTaskDescException.class,
                () -> TaskParser.validateParseAndCreateTask("todo"));
    }

    @Test
    void validateParseAndCreateTask_missingSpaceAfterCommandWord_throwsMissingSpace() {
        assertThrows(MissingSpaceAfterCommandWordException.class,
                () -> TaskParser.validateParseAndCreateTask("todoBuy milk"));
    }

    @Test
    void validateParseAndCreateTask_invalidCommand_throwsInvalidCommand() {
        assertThrows(InvalidCommandException.class,
                () -> TaskParser.validateParseAndCreateTask("unknown task"));
    }

    @Test
    void validateParseAndCreateTask_missingMarker_throwsMissingMarker() {
        assertThrows(MissingMarkerException.class,
                () -> TaskParser.validateParseAndCreateTask("deadline submit report"));
    }

    @Test
    void validateParseAndCreateTask_missingMarkerDesc_throwsMissingMarkerDesc() {
        assertThrows(MissingMarkerDescException.class,
                () -> TaskParser.validateParseAndCreateTask("deadline submit report /by "));
    }

    @Test
    void validateParseAndCreateTask_markersOutOfOrder_throwsMarkersOutOfOrder() {
        assertThrows(MarkersOutOfOrderException.class,
                () -> TaskParser.validateParseAndCreateTask("event meetup /to6pm /from5pm"));
    }

    @Test
    void validateParseAndCreateTask_eventMissingMarkerDesc_throwsMissingMarkerDesc() {
        assertThrows(MissingMarkerDescException.class,
                () -> TaskParser.validateParseAndCreateTask("event meetup /from5pm /to "));
    }
}