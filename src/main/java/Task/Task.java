package Task;

public class Task {
    protected String taskName;
    protected boolean isDone;

    Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    public String toString() {
        String state = isDone ? "[X]" : "[ ]";
        return state + " " + taskName;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    boolean isSame(String taskName) {
        if (this.taskName.equals(taskName)) {
            return true;
        }
        return false;
    }

    String getTaskName() {
        return taskName;
    }

    protected void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String toFileFormat() {
        return "Task" + (isDone ? "  Done  " : "Not Done") + " | " + getTaskName();
    }

    static public Task fromFileFormat(String fileFormat) {
        String [] parts = fileFormat.split(" \\| ",3);
        boolean done = parts[1].equals("  Done  ");
        Task task;
        System.out.println(parts[1]);
        switch (parts[0]) {
            case "T": task = Todo.fromFileFormat(parts[2]);
            break;
            case "D": task = Deadline.fromFileFormat(parts[2]);
            break;
            case "E": task = Event.fromFileFormat(parts[2]);
            break;
            default: task = new Task(parts[0]);
        }
        if (done) {
            task.mark();
        } else {
            task.unmark();
        }
        return task;
    }
}
