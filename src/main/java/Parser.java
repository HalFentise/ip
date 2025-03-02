import Task.*;
import Exceptions.*;
import java.io.*;

/**
 * This class is responsible for parsing user input and executing the corresponding command.
 * It interacts with the task list, UI, and storage to manage tasks.
 */
public class Parser {

    /**
     * Parses the given input command and performs the corresponding action.
     * It handles commands for listing, adding, deleting, marking, unmarking tasks, find
     * and quitting the program.
     *
     * @param input the user input command as a string
     * @param taskList the list of tasks to perform actions on
     * @param ui the user interface to show feedback
     * @param storage the storage to save the tasks after modification
     */
    public static void parse(String input, TaskList taskList, Ui ui, Storage storage) {
        String[] parts = input.split("\\s+", 2);
        String command = parts[0].toLowerCase();

        // Command handling
        try {
            switch (command) {
                case "list":
                    if (parts.length == 1) {
                        ui.showTasks(taskList.getTasks());
                    } else {
                        switch (parts[1].toLowerCase()) {
                            case "todo":
                                ui.showTasks(taskList.getTodoTasks());
                                break;
                            case "event":
                                ui.showTasks(taskList.getEventTasks());
                                break;
                            case "deadline":
                                ui.showTasks(taskList.getDeadlineTasks());
                                break;
                            default:
                                throw new TaskException("This is an invalid command");
                        }
                    }
                    break;
                case "todo":
                    if (parts.length < 2) {
                        throw new TaskException("The description of a todo cannot be empty.");
                    }
                    Task todo = new Todo(parts[1]);
                    taskList.addTask(todo);
                    storage.saveTasks(taskList.getTasks());
                    ui.showTaskAdded(todo, taskList.getTasks().size());
                    break;
                case "event":
                    if (parts.length < 2) {
                        throw new TaskException("The description of an event cannot be empty.");
                    }
                    String[] eventString = parts[1].split(" /from ");
                    if (eventString.length == 2) {
                        String name = eventString[0];
                        String[] time = eventString[1].split(" /to ");
                        if (time.length == 2) {
                            String startTime = time[0];
                            String endTime = time[1];
                            Task event = new Event(name, startTime, endTime);
                            taskList.addTask(event);
                            storage.saveTasks(taskList.getTasks());
                            ui.showTaskAdded(event, taskList.getTasks().size());
                        } else {
                            throw new TaskException("Invalid event format.\n" +
                                    "Valid format: event description /from startTime /to endTime");
                        }
                    } else {
                        throw new TaskException("Invalid event format.\n" +
                                "Valid format: event description /from startTime /to endTime");
                    }
                    break;
                case "deadline":
                    if (parts.length < 2) {
                        throw new TaskException("The description of a deadline cannot be empty.");
                    }
                    String[] deadlineString = parts[1].split(" /by ");
                    if (deadlineString.length == 2) {
                        String taskName = deadlineString[0];
                        String time = deadlineString[1];
                        Task deadline = new Deadline(taskName, time);
                        taskList.addTask(deadline);
                        storage.saveTasks(taskList.getTasks());
                        ui.showTaskAdded(deadline, taskList.getTasks().size());
                    } else {
                        throw new TaskException("Invalid deadline format.\n" +
                                "Valid format: deadline description /by time");
                    }
                    break;
                case "delete":
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
                    if (parts.length < 2 || !parts[1].matches("\\d+")) {
                        throw new TaskException("Please specify a valid task number to mark.");
                    }
                    int markIndex = Integer.parseInt(parts[1]) - 1;
                    taskList.markTask(markIndex);
                    storage.saveTasks(taskList.getTasks());
                    ui.showTaskMarked(taskList.getTask(markIndex));
                    break;
                case "unmark":
                    if (parts.length < 2 || !parts[1].matches("\\d+")) {
                        throw new TaskException("Please specify a valid task number to unmark.");
                    }
                    int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                    taskList.unmarkTask(unmarkIndex);
                    storage.saveTasks(taskList.getTasks());
                    ui.showTaskUnmarked(taskList.getTask(unmarkIndex));
                    break;
                case "bye":
                    System.out.println("Goodbye! Hope to see you again!");
                    ui.showLine();
                    System.exit(0);
                    break;
                case "find":
                    if (parts.length < 2) {
                        throw new TaskException("The description of a deadline cannot be empty.");
                    } else {
                        ui.showTasks(taskList.findTask(parts[1]));
                    }
                    break;
                default:
                    throw new TaskException("This is an invalid command");
            }
        } catch (TaskException | IOException e) {
            ui.showError(e.getMessage());
        }
    }
}

