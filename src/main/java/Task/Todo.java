package Task;

public class Todo extends Task {
    Todo(String taskName) {
        super(taskName);
    }

    public String toString() {
        String state = isDone ? "[X]" : "[ ]";
        return "[T]" + state + " " + taskName;
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "  Done  " : "Not Done") + " | " + getTaskName();
    }

    static public Task fromFileFormat(String fileFormat) {
        return new Todo (fileFormat);
    }
}
