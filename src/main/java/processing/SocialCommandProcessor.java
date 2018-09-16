package processing;

import user.Message;
import user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implementation of CommandProcessor to process all users inputs and return formatted outputs.
 */
public class SocialCommandProcessor extends CommandProcessor {

    private HashMap<String, User> userList;
    private MessageFormatter messageFormatter;

    public SocialCommandProcessor() {
        this.userList = new HashMap<String, User>();
        this.messageFormatter = new MessageFormatter();
    }

    /**
     * Classify the user's input to allow further processing to take place.
     * @param message user's input.
     * @return CommandType enum based on the action taken.
     */
    private CommandType getCommandResult(String message) {
        //TODO: Improve classification
        if (message.contains(" -> ")) {
            return CommandType.POSTING;
        } else if (message.contains(" follows ")) {
            return CommandType.FOLLOWING;
        } else if (message.contains(" wall")) {
            return CommandType.WALL;
        } else if (!message.contains(" ")) {
            return CommandType.READING;
        } else {
            return CommandType.INVALID;
        }
    }

    /**
     * Process the users input, deciding the requested action to take, performing the action, then returning an output to print.
     * @param userInput user's inputted string.
     * @param commandDate date command entered.
     * @return array of output strings.
     */
    @Override
    public List<String> processMessage(String userInput, LocalDateTime commandDate) {
        CommandType command = this.getCommandResult(userInput);
        List<String> output = new ArrayList<>();

        if (command == CommandType.POSTING) { //user posting a new message - e.g Alice -> Bob
            String username = userInput.substring(0, userInput.indexOf(" -> "));
            String userMessage = userInput.substring(userInput.indexOf(" -> ") + 4, userInput.length());
            Message tempMessage = new Message(username, userMessage, commandDate);
            if (!userList.containsKey(username)) {
                User user = new User(username);
                user.addMessage(tempMessage);
                userList.put(username, user);
            } else {
                userList.get(username).addMessage(tempMessage);
            }
        } else if (command == CommandType.READING) { //e.g. Alice, return list of messages
            if (userList.containsKey(userInput)) {
                return messageFormatter.formatReadMessages(userList.get(userInput).getUserMessages());
            }
        } else if (command == CommandType.FOLLOWING) { //e.g. Alice follows Bob
            String username = userInput.substring(0, userInput.indexOf(" follows "));
            String userToFollow = userInput.substring(userInput.indexOf(" follows ") + 9, userInput.length());
            userList.get(username).followUser(userToFollow);
        } else if (command == CommandType.WALL) { //e.g. Bob wall, return list of messages
            String username = userInput.substring(0, userInput.indexOf(" wall"));
            if (userList.containsKey(username)) {
                return messageFormatter.formatWallMessages(userList, username);
            }
        }

        return output;
    }

}
