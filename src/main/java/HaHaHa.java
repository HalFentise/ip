import java.util.Scanner;

import Task.*;
import Exceptions.*;

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
                try {
                    String[] parts = command.split("\\s+");
                    if (parts.length == 1) {
                        throw new InformationError("if you want to mark a task, please let me know the task No.\neg. mark 1");
                    }
                    int index = Integer.parseInt(parts[parts.length - 1]);
                    tasklist.mark(index);
                } catch (InformationError e) {
                    System.out.println(e.getMessage());
                    System.out.println("--------------------------------");
                }
                continue;
            }
            if (command.startsWith("unmark")) {
                try {
                    String[] parts = command.split("\\s+");
                    if (parts.length == 1) {
                        throw new InformationError("if you want to mark a task, please let me know the task No.\neg. mark 1");
                    }
                    int index = Integer.parseInt(parts[parts.length - 1]);
                    tasklist.unmark(index);
                } catch (InformationError e) {
                    System.out.println(e.getMessage());
                    System.out.println("--------------------------------");
                }
            continue;
            }
            tasklist.add(command);
        }
    }
}
