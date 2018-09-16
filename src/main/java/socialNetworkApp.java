import processing.SocialCommandProcessor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class socialNetworkApp {

    /**
     * Main method to start console for user input.
     * @param args - ignored.
     */
    public static void main (String args[]) {

        SocialCommandProcessor socialCommandProcessor = new SocialCommandProcessor();
        String userInput;

        System.out.println("Welcome to a command line social network.");
        System.out.println("Please enter your commands below (type 'exit' to quit):");

        try (Scanner scanner = new Scanner(System.in)) {
            while(scanner.hasNextLine() && !((userInput = scanner.nextLine()).equals("exit"))) {
                List<String> displayOutput = socialCommandProcessor.processMessage(userInput, LocalDateTime.now());
                for (String output : displayOutput) {
                    System.out.println(output);
                }
            }
        } finally {
            System.out.println("Program closing.");
        }
    }
}
