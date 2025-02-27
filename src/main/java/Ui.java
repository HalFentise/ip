import java.util.Scanner;
import java.util.ArrayList;
import Task.*;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void welcome() {
        System.out.println("--------------------------------");
        System.out.println("please notice that\n" +
                "the input date format should be: d/m/yyyy\n" +
                "Sorry for bring any inconvenience to you.");
        System.out.println("Hello, I am HaHaHa!\nWhat can I do for you?");
        System.out.println("--------------------------------");
    }

    public void bye() {
        System.out.println("Bye, see you next time!");
        System.out.println("--------------------------------");
    }

    public void showLine() {
        System.out.println("--------------------------------");
    }

    public String getUserInput() {
        System.out.print("Enter command: ");
        return scanner.nextLine();
    }

    public void showTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list. Go and add something!");
        } else {
            System.out.println("Here are your tasks:\n");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i));
            }
        }
        showLine();
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
        showLine();
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        showLine();
    }

    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        showLine();
    }
}
