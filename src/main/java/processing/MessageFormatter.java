package processing;

import user.Message;
import user.User;

import java.util.*;

/**
 * Helper class to format and correctly sort messages' outputs.
 */
public class MessageFormatter {

    /**
     * Take list of messages and return a correctly formatted list for read command.
     * @param messages list of messages
     * @return array of pretty strings.
     */
    public List<String> formatReadMessages (List<Message> messages) {
        List<String> outputStrings = new ArrayList<>();
        messages.sort(Comparator.comparing(Message::getDateposted, Comparator.reverseOrder()));

        for (Message message : messages) {
            outputStrings.add(message.toPrettyString());
        }
        return outputStrings;
    }

    /**
     * Take map of all users and the current user posting the wall command and return formatted output.
     * @param usersList map of all users.
     * @param username current user.
     * @return array of prettyWall strings.
     */
    public List<String> formatWallMessages (HashMap<String, User> usersList, String username) {

        List<String> outputStrings = new ArrayList<>();
        List<Message> allMessages = new ArrayList<>();

        HashSet<String> followingUsers = usersList.get(username).getUsersFollowed();
        followingUsers.add(username); //add current user to generate all messages

        for (String user : followingUsers) {
            for (Message message : usersList.get(user).getUserMessages()) {
                allMessages.add(message);
            }
        }
        allMessages.sort(Comparator.comparing(Message::getDateposted, Comparator.reverseOrder()).thenComparing(Message::getUsername));
        for (Message m : allMessages) {
            outputStrings.add(m.toPrettyWallString());
        }
        return outputStrings;
    }
}
