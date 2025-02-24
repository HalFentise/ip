package Task;

import Exceptions.InformationError;
import Exceptions.TaskException;
import Exceptions.UnSupportCommandException;

import java.util.ArrayList;
import java.io.*;

public class TaskManager {
    private ArrayList<Task> taskList = new ArrayList<>();
    private static final String FILE_PATH = "./data/list.txt";

    public TaskManager() {
        loadTasks();
    }

    public void add(String taskString) {
        Task task;

        String[] parts = taskString.split("\\s+",2);
        String type = parts[0];

        try {
            if (type.equals("delete")) {
                if (parts.length < 2 || !parts[1].matches("\\d+")) {
                    throw new InformationError("Please specify a valid task number to delete.");
                }
                int index = Integer.parseInt(parts[1]);
                delete(index);
                return;
            } else if (type.equals("todo")) {
                if (parts.length < 2) {
                    throw new InformationError("The description of Todo task cannot be empty, can you add it later?");
                }
                String name = parts[1];
                task = new Todo(name);
            } else if (type.equals("deadline")) {
                if (parts.length < 2) {
                    throw new InformationError("The description of Deadline task cannot be empty, can you add it later?");
                }
                String deadline = parts[1];
                task = new Deadline(deadline);
            } else if (type.equals("event")) {
                if (parts.length < 2) {
                    throw new InformationError("The description of Event task cannot be empty, can you add it later?");
                }
                String event = parts[1];
                task = new Event(event);
            } else {
                throw new UnSupportCommandException("Sorry, I don't know what is this mean (TwT)\n" +
                        "I cannot read uppercase letters, if you use uppercase in your command, maybe you can use lowercase letters?");
            }
        } catch (TaskException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------");
            return;
        }

        //add to list
        taskList.add(task);
        saveTasks();
        System.out.println("added: " + task.getTaskName());
        System.out.println("Now you have " + taskList.size() + " in the list.");
        System.out.println("--------------------------------");
    }

    public void list() {
        if (taskList.isEmpty()) {
            System.out.println("No task in the list, go and add something!");
            System.out.println("--------------------------------");
            return;
        }
        System.out.println("[X] means done, you can check your work here.");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, taskList.get(i));
        }
        System.out.println("--------------------------------");
    }

    //mark an unmark task
    public void mark(int index) {
        if (index > taskList.size() || index <= 0) {
            System.out.println("this task is not exist :(");
            System.out.println("--------------------------------");
            return;
        }
        Task task = taskList.get(index - 1);
        if (task == null) {
            System.out.println("Task not found.");
        } else {
            task.mark();
            System.out.println("Great! This task has been marked done!");
            System.out.println(index + ". " + task);
            System.out.println("--------------------------------");
        }
        saveTasks();
    }

    public void unmark(int index) {
        if (index > taskList.size() || index < 0) {
            System.out.println("this task is not exist :(");
            System.out.println("--------------------------------");
            return;
        }
        Task task = taskList.get(index - 1);
        if (task == null) {
            System.out.println("task not found");
        }
        else {
            task.unmark();
            System.out.println("Unmark this task, you need to finish it later!");
            System.out.println(index + ". " + task);
            System.out.println("--------------------------------");
        }
        saveTasks();
    }
    // delete task
    public void delete(int index) {
        if (index <= 0 || index > taskList.size()) {
            System.out.println("this task is not exist :(");
            System.out.println("--------------------------------");
            return;
        }
        Task removedTask = taskList.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("--------------------------------");
        saveTasks();
    }
    //save

    private void saveTasks() {
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();

        try {
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : taskList) {
                if (task == null) {
                    return;
                }
                writer.write(task.toFileFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                taskList.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}
