package test.java.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskParserTester {

    @Test
    public void sampleTest() {
        try {
            assertEquals(2, 1 + 1);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }
}
