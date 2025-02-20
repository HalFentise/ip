package Task;

import Exceptions.InformationError;
import Exceptions.TaskException;
import Exceptions.UnSupportCommandException;

import java.util.ArrayList;
import java.io.*;

public class TaskManager {
    private ArrayList<Task> taskList = new ArrayList<>();
    private static final String FILE_PATH = "./data/list.txt";

    public void add(String taskString) {
        Task task = null;

        String[] parts = taskString.split("\\s+",2);
        String type = parts[0];

        try {
            if (type.equals("delete")) {
                if (parts.length < 2 || !parts[1].matches("\\d+")) {
                    throw new InformationError("Please specify a valid task number to delete.");
                }
                int index = Integer.parseInt(parts[1]);
                delete(index);
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
                String name = deadline.split(" /by ")[0];
                String time = deadline.split(" /by ")[1];
                task = new Deadline(name, time);
            } else if (type.equals("event")) {
                if (parts.length < 2) {
                    throw new InformationError("The description of Event task cannot be empty, can you add it later?");
                }
                String event = parts[1];
                String name = event.split(" /from ")[0];
                String time = event.split(" /from ", 2)[1];
                String startTime = time.split(" /to ")[0];
                String endTime = time.split(" /to ", 2)[1];
                task = new Event(name, startTime, endTime);
            } else {
                throw new UnSupportCommandException("Sorry, I don't know what is this mean (T⌓T)");
            }
        } catch (TaskException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------");
            return;
        }

        //add to list
        taskList.add(task);
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
    //search the task
    Task search(String taskName) {
        for (Task tasks : taskList) {
            if (tasks.isSame(taskName)) {
                return tasks;
            }
        }
        return null;
    }
    //mark an unmark task
    public void mark(int index) {
        if (index > taskList.size() || index <= 0) {
            System.out.println("Index is out of range :(");
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
    }

    public void unmark(int index) {
        if (index > taskList.size() || index < 0) {
            System.out.println("index is out of range :(");
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
    }
    // delete task
    public void delete(int index) {
        if (index <= 0 || index > taskList.size()) {
            System.out.println("Index is out of range :(");
            System.out.println("--------------------------------");
            return;
        }
        Task removedTask = taskList.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("--------------------------------");
    }
    //save

    private void saveTasks() {
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();

        try {
            // 如果目录不存在，创建目录
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            // 写入任务到文件
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : taskList) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
