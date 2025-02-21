package Task;

import Exceptions.*;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String taskString) {
        super(taskString);
        String[] parts = taskString.split(" by ");
        if (parts.length == 2) {
            setTaskName(parts[0]);
            this.deadline = parts[1];
        } else {
            throw new IllegalArgumentException("Invalid event time format.");
        }
    }

    public String toString() {
        String state = isDone ? "[X]" : "[ ]";
        return "[D]" + state + " " + taskName + " (by:" + deadline + " )";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "  Done  " : "Not Done") + " | " + getTaskName();
    }
}
