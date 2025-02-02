import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        TaskList tasklist = new TaskList();
        //greeting
        Command.greeting();

        //main loop
        while(true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                Command.bye();
                return;
            }
            tasklist.add(command);
        }
    }
}
