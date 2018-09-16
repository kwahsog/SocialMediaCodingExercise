package processing;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SocialCommandProcessorTest {

    @Test
    void processMessageTest() {
        SocialCommandProcessor socialCommandProcessor = new SocialCommandProcessor();
        List<String> output = new ArrayList<>();

        output = socialCommandProcessor.processMessage("Alice -> Weather is great", LocalDateTime.now().minus(Duration.ofSeconds(300)));
        assertEquals(0, output.size());
        output = socialCommandProcessor.processMessage("Bob -> Weather is ok", LocalDateTime.now().minus(Duration.ofSeconds(60)));
        assertEquals(0, output.size());
        output = socialCommandProcessor.processMessage("Bob -> Weather is bad", LocalDateTime.now().minus(Duration.ofSeconds(120)));
        assertEquals(0, output.size());

        output = socialCommandProcessor.processMessage("Alice", LocalDateTime.now());
        assertEquals(1, output.size());
        assertEquals("Weather is great (5 minutes ago)", output.get(0));

        output = socialCommandProcessor.processMessage("Bob", LocalDateTime.now());
        assertEquals(2, output.size());
        assertEquals("Weather is ok (1 minute ago)", output.get(0));
        assertEquals("Weather is bad (2 minutes ago)", output.get(1));

        output = socialCommandProcessor.processMessage("Charlie -> in NY!", LocalDateTime.now().minus(Duration.ofSeconds(2)));
        assertEquals(0, output.size());

        output = socialCommandProcessor.processMessage("Charlie follows Alice", LocalDateTime.now());
        assertEquals(0, output.size());

        output = socialCommandProcessor.processMessage("Charlie wall", LocalDateTime.now());
        assertEquals(2, output.size());
        assertEquals("Charlie - in NY! (2 seconds ago)", output.get(0));
        assertEquals("Alice - Weather is great (5 minutes ago)", output.get(1));

        output = socialCommandProcessor.processMessage("Charlie follows Bob", LocalDateTime.now());
        assertEquals(0, output.size());

        output = socialCommandProcessor.processMessage("Charlie wall", LocalDateTime.now());
        assertEquals(4, output.size());
        assertEquals("Charlie - in NY! (2 seconds ago)", output.get(0));
        assertEquals("Bob - Weather is ok (1 minute ago)", output.get(1));
        assertEquals("Bob - Weather is bad (2 minutes ago)", output.get(2));
        assertEquals("Alice - Weather is great (5 minutes ago)", output.get(3));


    }

}
