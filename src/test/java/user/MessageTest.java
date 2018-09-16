package user;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases for Message class.
 */
public class MessageTest {

    @Test
    void toPrettyStringTest() {
        Message messageOne = new Message("Bob", "Hello world", LocalDateTime.now().minus(Duration.ofSeconds(10)));
        Message messageTwo = new Message("Fred", "Late message", LocalDateTime.now().minus(Duration.ofSeconds(100)));
        Message messageThree = new Message("Alice", "Later message", LocalDateTime.now().minus(Duration.ofSeconds(120)));
        Message messageFour = new Message("Alice", "Latest message", LocalDateTime.now().minus(Duration.ofSeconds(606)));
        Message messageFive = new Message("Alice", "First message", LocalDateTime.now().minus(Duration.ofSeconds(1)));

        assertEquals("Hello world (10 seconds ago)", messageOne.toPrettyString());
        assertEquals("Late message (1 minute ago)", messageTwo.toPrettyString());
        assertEquals("Later message (2 minutes ago)", messageThree.toPrettyString());
        assertEquals("Latest message (10 minutes ago)", messageFour.toPrettyString());
        assertEquals("First message (1 second ago)", messageFive.toPrettyString());
    }

    @Test
    void toPrettyWallStringTest() {
        Message messageOne = new Message("Bob", "Hello world", LocalDateTime.now().minus(Duration.ofSeconds(10)));
        Message messageTwo = new Message("Fred", "Late message", LocalDateTime.now().minus(Duration.ofSeconds(100)));
        Message messageThree = new Message("Alice", "Later message", LocalDateTime.now().minus(Duration.ofSeconds(120)));
        Message messageFour = new Message("Alice", "Latest message", LocalDateTime.now().minus(Duration.ofSeconds(606)));
        Message messageFive = new Message("Alice", "First message", LocalDateTime.now().minus(Duration.ofSeconds(1)));

        assertEquals("Bob - Hello world (10 seconds ago)", messageOne.toPrettyWallString());
        assertEquals("Fred - Late message (1 minute ago)", messageTwo.toPrettyWallString());
        assertEquals("Alice - Later message (2 minutes ago)", messageThree.toPrettyWallString());
        assertEquals("Alice - Latest message (10 minutes ago)", messageFour.toPrettyWallString());
        assertEquals("Alice - First message (1 second ago)", messageFive.toPrettyWallString());
    }
}
