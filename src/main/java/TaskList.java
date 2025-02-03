import java.util.Arrays;

public class TaskList {
    private Task[] taskList = new Task[0];

    public void add(String taskName) {
        Task task = new Task(taskName);
        taskList = Arrays.copyOf(taskList,taskList.length + 1);
        taskList[taskList.length-1] = task;
        System.out.println("added: " + taskName);
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
        Task task = taskList[index - 1];
        if (task == null) {
            System.out.println("task not found");
        }
        else {
            task.mark();
            System.out.println("Great! This task has been marked done!");
            System.out.println(index + " " + task);
            System.out.println("--------------------------------");
        }
    }

    void unmark(int index) {
        Task task = taskList[index - 1];
        if (task == null) {
            System.out.println("task not found");
        }
        else {
            task.unmark();
            System.out.println("Unmark this task, you need to finish it later!");
            System.out.println(index + " " + task);
            System.out.println("--------------------------------");
        }
    }
}
