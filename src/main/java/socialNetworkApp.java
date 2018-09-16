import processing.SocialCommandProcessor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class socialNetworkApp {

    public static void main (String args[]) {

        SocialCommandProcessor socialCommandProcessor = new SocialCommandProcessor();
        String userInput;
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to a command line social network.");
        System.out.println("Please enter your commands below:");

        while (true) {
            userInput = in.nextLine();
            List<String> displayOutput = socialCommandProcessor.processMessage(userInput, LocalDateTime.now());
            for (String output : displayOutput) {
                System.out.println(output);
            }
        }
    }
}
