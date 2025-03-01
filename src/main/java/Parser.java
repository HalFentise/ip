import Task.*;
import Exceptions.*;
import java.io.*;

public class Parser {
    public static void parse(String input, TaskList taskList, Ui ui, Storage storage) {
        String[] parts = input.split("\\s+", 2);
        String command = parts[0].toLowerCase();

        //command handle
        try {
            switch (command) {
                case "list":
                    ui.showTasks(taskList.getTasks());
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
                        throw new TaskException("The description of a event cannot be empty.");
                    }
                    String[] eventString = parts[1].split(" /from ");
                    if (eventString.length == 2) {
                        String name = eventString[0];
                        String[] time = eventString[1].split(" /to ");
                        if (time.length == 2) {
                            String startTime = time[0];
                            String endTime = time[1];
                            Task event = new Event(name,startTime,endTime);
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
                    ui.showTasks(taskList.getTasks());
                    break;
                case "unmark":
                    if (parts.length < 2 || !parts[1].matches("\\d+")) {
                        throw new TaskException("Please specify a valid task number to unmark.");
                    }
                    int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                    taskList.unmarkTask(unmarkIndex);
                    storage.saveTasks(taskList.getTasks());
                    ui.showTasks(taskList.getTasks());
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
                    throw new TaskException("Unknown command.");
            }
        } catch (TaskException | IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
