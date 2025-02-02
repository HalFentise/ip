public class Task {
    private String taskName;
    private boolean isDone;

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
}
