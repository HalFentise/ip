package Task;

import Exceptions.*;

public class Deadline extends Task {
    private String deadlineTime;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadlineTime = deadline;
    }

    public String toString() {
        String state = isDone ? "[X]" : "[ ]";
        return "[D]" + state + " " + taskName + " (by:" + deadlineTime + " )";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "  Done  " : "Not Done") + " | " + getTaskName() + " (by: " + deadlineTime + ")";
    }

    static public Task fromFileFormat(String fileFormat) {
        String[] parts = fileFormat.split(" \\(by: ");
        String time = parts[1].substring(0, parts[1].length() - 2);
        return new Deadline(parts[0], time);
    }
}
