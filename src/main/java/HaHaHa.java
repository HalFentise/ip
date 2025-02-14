import java.util.Scanner;

import Task.*;

public class HaHaHa {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        TaskManager tasklist = new TaskManager();

        //greeting
        Special.greeting();

        //main loop
        while(true) {
            command = scanner.nextLine();

            //bye
            if (command.equals("bye")) {
                Special.bye();
                return;
            }

            //detect command
            if (command.equals("list")) {
                tasklist.list();
                continue;
            }
            if (command.startsWith("mark")) {
                String[] parts = command.split("\\s+");
                int index = Integer.parseInt(parts[parts.length - 1]);
                tasklist.mark(index);
                continue;
            }
            if (command.startsWith("unmark")) {
                String[] parts = command.split("\\s+");
                int index = Integer.parseInt(parts[parts.length - 1]);
                tasklist.unmark(index);
                continue;
            }
            tasklist.add(command);
        }
    }
}
