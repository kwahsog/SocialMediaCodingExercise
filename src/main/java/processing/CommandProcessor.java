package processing;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Base class for CommandProcessors
 */
public abstract class CommandProcessor {

    /**
     * Process the user's input.
     * @param userInput user's inputted string.
     * @param commandDate date command entered.
     * @return array of output strings.
     */
    abstract public List<String> processMessage(String userInput, LocalDateTime commandDate);

}
