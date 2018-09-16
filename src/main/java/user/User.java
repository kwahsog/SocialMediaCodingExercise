package user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * User class to store record of user's messages and who the user is following.
 */
public class User {

    private String name;
    public List<Message> userMessages;
    public HashSet<String> usersFollowed;

    public User (String name) {
        this.setName(name);
        this.userMessages = new ArrayList<Message>();
        this.usersFollowed = new HashSet<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Store a given message for this user.
     * @param message message to store.
     */
    public void addMessage(Message message) {
        userMessages.add(message);
    }

    /**
     * Return all user's messages.
     * @return list of user's messages.
     */
    public List<Message> getUserMessages() {
        return userMessages;
    }

    /**
     * Delete record of all user's messages.
     */
    public void deleteMessages() {
        userMessages.clear();
    }

    /**
     * Return all users who the user is following.
     * @return HashSet of users.
     */
    public HashSet<String> getUsersFollowed() { return usersFollowed; }

    /**
     * Follow a given user.
     * @param username user's name.
     */
    public void followUser(String username) {
        if (!usersFollowed.contains(username)) {
            usersFollowed.add(username);
        }
    }
}
