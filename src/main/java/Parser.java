import Task.*;
import Exceptions.*;
import java.io.*;

/**
 * A utility class for parsing and executing user commands.
 * Interacts with the task list, user interface, and storage to handle various commands.
 */
public class Parser {

    /**
     * Parses and executes a user command.
     *
     * @param input     the user's input command as a string
     * @param taskList  the task list to perform operations on
     * @param ui        the user interface for displaying output
     * @param storage   the storage system for saving and loading tasks
     */
    public static void parse(String input, TaskList taskList, Ui ui, Storage storage) {
        String[] parts = input.split("\\s+", 2); // Split the input into command and arguments
        String command = parts[0].toLowerCase(); // Extract the command and convert to lowercase

        try {
            switch (command) {
                case "list":
                    // Display all tasks in the task list
                    ui.showTasks(taskList.getTasks());
                    break;
                case "todo":
                    // Add a new todo task
                    if (parts.length < 2) {
                        throw new TaskException("The description of a todo cannot be empty.");
                    }
                    Task todo = new Todo(parts[1]);
                    taskList.addTask(todo);
                    storage.saveTasks(taskList.getTasks());
                    ui.showTaskAdded(todo, taskList.getTasks().size());
                    break;
                case "event":
                    // Add a new event task
                    if (parts.length < 2) {
                        throw new TaskException("The description of an event cannot be empty.");
                    }
                    Task event = new Event(parts[1]);
                    taskList.addTask(event);
                    storage.saveTasks(taskList.getTasks());
                    ui.showTaskAdded(event, taskList.getTasks().size());
                    break;
                case "deadline":
                    // Add a new deadline task
                    if (parts.length < 2) {
                        throw new TaskException("The description of a deadline cannot be empty.");
                    }
                    Task deadline = new Deadline(parts[1]);
                    taskList.addTask(deadline);
                    storage.saveTasks(taskList.getTasks());
                    ui.showTaskAdded(deadline, taskList.getTasks().size());
                    break;
                case "delete":
                    // Delete a task by its index
                    if (parts.length < 2 || !parts[1].matches("\\d+")) {
                        throw new TaskException("Please specify a valid task number to delete.");
                    }
                    int deleteIndex = Integer.parseInt(parts[1]) - 1;
                    Task removedTask = taskList.getTask(deleteIndex);
                    taskList.deleteTask(deleteIndex);
                    storage.saveTasks(taskList.getTasks());
                    ui.showTaskDeleted(removedTask, taskList.getTasks().size());
                    break;
                case "mark":
                    // Mark a task as completed
                    if (parts.length < 2 || !parts[1].matches("\\d+")) {
                        throw new TaskException("Please specify a valid task number to mark.");
                    }
                    int markIndex = Integer.parseInt(parts[1]) - 1;
                    taskList.markTask(markIndex);
                    storage.saveTasks(taskList.getTasks());
                    ui.showTasks(taskList.getTasks());
                    break;
                case "unmark":
                    // Unmark a task (mark it as not completed)
                    if (parts.length < 2 || !parts[1].matches("\\d+")) {
                        throw new TaskException("Please specify a valid task number to unmark.");
                    }
                    int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                    taskList.unmarkTask(unmarkIndex);
                    storage.saveTasks(taskList.getTasks());
                    ui.showTasks(taskList.getTasks());
                    break;
                case "bye":
                    // Exit the application
                    System.out.println("Goodbye! Hope to see you again!");
                    ui.showLine();
                    System.exit(0);
                default:
                    // Handle unknown commands
                    throw new TaskException("Unknown command.");
            }
        } catch (TaskException | IOException e) {
            // Display error messages for exceptions
            ui.showError(e.getMessage());
        }
    }
}

