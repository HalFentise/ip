import java.util.Arrays;

public class TaskList {
    private Task[] taskList = new Task[0];

    public void add(String taskName) {
        if (taskName.equals("list")) {
            list();
            return;
        }
        Task task = new Task(taskName);
        taskList = Arrays.copyOf(taskList,taskList.length + 1);
        taskList[taskList.length-1] = task;
        System.out.println("added: " + task);
        System.out.println("--------------------------------");
    }

    public void list() {
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
}
