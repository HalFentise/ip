import java.util.Arrays;

public class TaskList {
    private Task[] taskList = new Task[0];

    public void add(String taskString) {
        Task task;

        //define task type
        String[] parts = taskString.split("\\s+",2);
        String type = parts[0];
        if (type.equals("todo")) {
            String name = parts[1];
            task = new Todo(name);
        } else if (type.equals("deadline")) {
            String deadline = parts[1];
            String name = deadline.split(" /by ")[0];
            String time = deadline.split(" /by ")[1];
            task = new Deadline(name, time);
        } else if (type.equals("event")) {
            String event = parts[1];
            String name = event.split(" /from ")[0];
            String time = event.split(" /from ",2)[1];
            String startTime = time.split(" /to ")[0];
            String endTime = time.split(" /to ",2)[1];
            task = new Event(name, startTime, endTime);
        } else {
            System.out.println("Sorry, I cannot recognize this task :(\n" +
                            "here are the types I supported:\ntodo, deadline, event");
            System.out.println("--------------------------------");
            return;
        }

        //add to list
        taskList = Arrays.copyOf(taskList,taskList.length + 1);
        taskList[taskList.length-1] = task;
        System.out.println("added: " + task.getTaskName());
        System.out.println("Now you have " + taskList.length + " in the list.");
        System.out.println("--------------------------------");
    }

    public void list() {
        if (taskList.length == 0) {
            System.out.println("No task in the list, go and add something!");
            System.out.println("--------------------------------");
            return;
        }
        System.out.println("[X] means done, you can check your work here.");
        for (int i = 0; i < taskList.length; i++) {
            System.out.printf("%d. " + taskList[i] + "\n",i + 1);
        }
        System.out.println("--------------------------------");
    }

    Task search(String taskName) {
        for (Task tasks : taskList) {
            if (tasks.isSame(taskName)) {
                return tasks;
            }
        }
        return null;
    }

    void mark(int index) {
        if (index > taskList.length || index < 0) {
            System.out.println("index is out of range :(");
            System.out.println("--------------------------------");
            return;
        }
        Task task = taskList[index - 1];
        if (task == null) {
            System.out.println("task not found");
        }
        else {
            task.mark();
            System.out.println("Great! This task has been marked done!");
            System.out.println(index + ". " + task);
            System.out.println("--------------------------------");
        }
    }

    void unmark(int index) {
        if (index > taskList.length || index < 0) {
            System.out.println("index is out of range :(");
            System.out.println("--------------------------------");
            return;
        }
        Task task = taskList[index - 1];
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
}
