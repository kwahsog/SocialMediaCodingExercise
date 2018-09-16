# Social network coding exercise

Coding exercise, implementing a console based social networking application. Please see provided .pdf for full spec and list of actions and valid commands.

Developed and tested using Java 8. Solution consists of a standard Maven project, using JUnit 5.2.0 for unit tests.

Code can be ran/tested via IDE or via Maven. Instructions via Maven below:

To test via console (from project's root directory):

`mvn test`

To run via console (from project's root directory):

`mvn clean install`

`java -jar target/socialmediacodingexercise-1.0-SNAPSHOT.jar`

### Extra considerations:
* As specified, focus is on valid input. Implementation of getCommandResult in SocialCommandProcessor could be improved/made more robust.
  * User names are assumed to have no spaces.
* An exit command has been added: if the user types 'exit' the program will close.
