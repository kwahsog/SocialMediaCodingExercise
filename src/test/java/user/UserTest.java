package user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Test cases for User class.
 */
public class UserTest {

    @Test
    void userMessagesTest() {
        Message messageOne = new Message("Bob", "Hello world", LocalDateTime.now().minus(Duration.ofSeconds(10)));
        Message messageTwo = new Message("Fred", "Late message", LocalDateTime.now().minus(Duration.ofSeconds(100)));
        User userOne = new User("userOne");
        assertEquals(0 , userOne.getUserMessages().size());

        userOne.addMessage(messageOne);
        assertEquals(1 , userOne.getUserMessages().size());
        assertEquals(messageOne, userOne.getUserMessages().get(0));

        userOne.addMessage(messageTwo);
        assertEquals(2 , userOne.getUserMessages().size());
        assertEquals(messageOne, userOne.getUserMessages().get(0));
        assertEquals(messageTwo, userOne.getUserMessages().get(1));

        userOne.deleteMessages();
        assertEquals(0 , userOne.getUserMessages().size());
    }

    @Test
    void userFollowersTest() {
        User userOne = new User("userOne");
        User userTwo = new User("userTwo");
        User userThree = new User("userThree");
        assertEquals(0 , userOne.getUsersFollowed().size());
        assertEquals(0 , userTwo.getUsersFollowed().size());
        assertEquals(0 , userThree.getUsersFollowed().size());

        userOne.followUser("userTwo");
        assertEquals(1 , userOne.getUsersFollowed().size());
        assertTrue(userOne.getUsersFollowed().contains("userTwo"));

        userOne.followUser("userThree");
        assertEquals(2 , userOne.getUsersFollowed().size());
        assertTrue(userOne.getUsersFollowed().contains("userThree"));
    }
}
