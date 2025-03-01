import java.util.Scanner;
import java.util.ArrayList;
import Task.*;

/**
 * Handles user interaction for the HaHaHa application.
 * Provides methods to display messages, retrieve user input, and show task-related information.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui instance and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void welcome() {
        System.out.println("--------------------------------");
        System.out.println("please notice that\n" +
                "the input date format should be: d/m/yyyy\n" +
                "Sorry for bring any inconvenience to you.");
        System.out.println("Hello, I am HaHaHa!\nWhat can I do for you?");
        System.out.println("--------------------------------");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void bye() {
        System.out.println("Bye, see you next time!");
        System.out.println("--------------------------------");
    }

    /**
     * Displays a line separator for visual clarity.
     */
    public void showLine() {
        System.out.println("--------------------------------");
    }

    /**
     * Prompts the user for input and returns the entered command as a string.
     *
     * @return the user's input command
     */
    public String getUserInput() {
        System.out.print("Enter command: ");
        return scanner.nextLine();
    }

    /**
     * Displays the list of tasks to the user.
     * If the task list is empty, prompts the user to add tasks.
     *
     * @param tasks the list of tasks to display
     */
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

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
        showLine();
    }

    /**
     * Displays a confirmation message when a task is added to the list.
     *
     * @param task       the task that was added
     * @param totalTasks the total number of tasks after the addition
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a confirmation message when a task is removed from the list.
     *
     * @param task       the task that was removed
     * @param totalTasks the total number of tasks after the removal
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        showLine();
    }
}

