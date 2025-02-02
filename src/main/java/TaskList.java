import java.util.Arrays;

public class TaskList {
    private String[] taskList = new String[0];

    public void add(String task) {
        if (task.equals("list")) {
            list();
            return;
        }
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
}
