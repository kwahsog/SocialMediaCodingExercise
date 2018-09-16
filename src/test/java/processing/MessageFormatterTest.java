package processing;

import org.junit.jupiter.api.Test;
import user.Message;
import user.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MessageFormatterTest {

    @Test
    void formatReadMessagesTest() {
        MessageFormatter messageFormatter = new MessageFormatter();
        Message messageOne = new Message("Bob", "Hello world", LocalDateTime.now().minus(Duration.ofSeconds(10)));
        Message messageTwo = new Message("Fred", "Late message", LocalDateTime.now().minus(Duration.ofSeconds(100)));
        Message messageThree = new Message("Alice", "Later message", LocalDateTime.now().minus(Duration.ofSeconds(120)));
        Message messageFour = new Message("Alice", "Latest message", LocalDateTime.now().minus(Duration.ofSeconds(606)));

        List<Message> messageList = new ArrayList<>();
        messageList.add(messageThree);
        messageList.add(messageFour);
        messageList.add(messageOne);
        messageList.add(messageTwo);
        List<String> formattedList = messageFormatter.formatReadMessages(messageList);

        assertEquals(4, formattedList.size());
        assertEquals(messageOne.toPrettyString(), formattedList.get(0));
        assertEquals(messageTwo.toPrettyString(), formattedList.get(1));
        assertEquals(messageThree.toPrettyString(), formattedList.get(2));
        assertEquals(messageFour.toPrettyString(), formattedList.get(3));
    }

    @Test
    void formatWallMessagesTest() {
        MessageFormatter messageFormatter = new MessageFormatter();
        HashMap<String, User> usersList = new HashMap<>();
        List<String> output = new ArrayList<>();

        User charlie = new User("Charlie");
        User bob = new User("Bob");
        User alice = new User("Alice");
        usersList.put("Charlie", charlie);
        usersList.put("Bob", bob);
        usersList.put("Alice", alice);
        Message messageOne = new Message("Charlie", "I'm in New York today! Anyone want to have a coffee?", LocalDateTime.now().minus(Duration.ofSeconds(2)));
        charlie.addMessage(messageOne);
        Message messageTwo = new Message("Alice", "I love the weather today", LocalDateTime.now().minus(Duration.ofSeconds(301)));
        alice.addMessage(messageTwo);
        Message messageThree = new Message("Bob", "GG tho", LocalDateTime.now().minus(Duration.ofSeconds(90)));
        Message messageFour = new Message("Bob", "we lost", LocalDateTime.now().minus(Duration.ofSeconds(120)));
        bob.addMessage(messageThree);
        bob.addMessage(messageFour);

        output = messageFormatter.formatWallMessages(usersList, "Charlie");
        assertEquals(1, output.size());
        assertEquals(messageOne.toPrettyWallString(), output.get(0));

        charlie.followUser("Alice");
        output = messageFormatter.formatWallMessages(usersList, "Charlie");
        assertEquals(2, output.size());
        assertEquals(messageOne.toPrettyWallString(), output.get(0));
        assertEquals(messageTwo.toPrettyWallString(), output.get(1));

        charlie.followUser("Bob");
        output = messageFormatter.formatWallMessages(usersList, "Charlie");
        assertEquals(4, output.size());
        assertEquals(messageOne.toPrettyWallString(), output.get(0));
        assertEquals(messageThree.toPrettyWallString(), output.get(1));
        assertEquals(messageFour.toPrettyWallString(), output.get(2));
        assertEquals(messageTwo.toPrettyWallString(), output.get(3));
    }
}
