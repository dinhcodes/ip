package ello.command.parser;

import ello.command.exception.InvalidDateTimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateTimeParserTest {
    @Test
    void parse_validDateOnlyFormats_defaultsToStartOfDay() {
        assertEquals(
                LocalDateTime.of(2019,12,2,0,0),
                DateTimeParser.parse("2/12/2019")
        );
        assertEquals(
                LocalDateTime.of(2019,12,2,0,0),
                DateTimeParser.parse("02/12/2019")
        );
    }

    @Test
    void parse_invalidOrEmpty_throwsInvalidDateTimeException() {
        assertThrows(InvalidDateTimeException.class, () -> DateTimeParser.parse("not-a-date"));
        assertThrows(InvalidDateTimeException.class, () -> DateTimeParser.parse(" "));
        assertThrows(InvalidDateTimeException.class, () -> DateTimeParser.parse(null));
    }

    @Test
    void format_validDateTime_returnsFormattedString() {
        LocalDateTime dt = LocalDateTime.of(2019,12,2,18,0);
        assertEquals("Dec022019,6:00 PM", DateTimeParser.format(dt));
    }

    @Test
    void format_null_returnsEmptyString() {
        assertEquals("", DateTimeParser.format(null));
    }
}