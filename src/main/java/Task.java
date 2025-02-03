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
}
