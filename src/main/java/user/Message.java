package user;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Message class to records of individual messages.
 */
public class Message {

    private String message;
    private String username;
    private LocalDateTime datePosted;

    public Message(String username, String message, LocalDateTime datePosted) {
        this.setUsername(username);
        this.setMessage(message);
        this.setDateposted(datePosted);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateposted() {
        return datePosted;
    }

    public void setDateposted(LocalDateTime dateposted) {
        this.datePosted = dateposted;
    }

    /**
     * Generate String for output when the user enters the wall command.
     * @return formatted string for the Message class.
     */
    public String toPrettyWallString() {
        return username + " - " + this.toPrettyString();
    }

    /**
     * Generate String for output when the user enters the read command.
     * @return formatted string for the Message class.
     */
    public String toPrettyString() {
        String prettyString = "";
        Duration dur = Duration.between(datePosted, LocalDateTime.now());
        long seconds = dur.getSeconds();
        if (seconds < 2) {
            prettyString = "(" + seconds + " second ago)";
        } else if (seconds < 60) {
            prettyString = "(" + seconds + " seconds ago)";
        } else if (seconds < 120) {
            prettyString = "(" + dur.toMinutes() + " minute ago)";
        } else {
            prettyString = "(" + dur.toMinutes() + " minutes ago)";
        }
        return message + " " + prettyString;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", username='" + username + '\'' +
                ", dateposted=" + datePosted +
                '}';
    }
}
