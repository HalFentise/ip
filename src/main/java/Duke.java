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

            //bye
            if (command.equals("bye")) {
                Command.bye();
                return;
            }

            //detect command
            if (command.equals("list")) {
                tasklist.list();
                continue;
            }
            if (command.contains("mark")) {
                String[] parts = command.split("\\s+");
                int index = Integer.parseInt(parts[parts.length - 1]);
                tasklist.mark(index);
                continue;
            }
            if (command.contains("unmark")) {
                String[] parts = command.split("\\s+");
                int index = Integer.parseInt(parts[parts.length - 1]);
                tasklist.unmark(index);
                continue;
            }
            tasklist.add(command);
        }
    }
}
