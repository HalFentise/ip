package Task;

import Exceptions.*;

/**
 * Represents a task with a specific deadline.
 * Extends the {@code Task} class to include a deadline attribute.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline.
     * The input string should be in the format: {@code "<taskName> by <deadline>"}.
     *
     * @param taskString the full description of the deadline task, including the deadline
     * @throws IllegalArgumentException if the format of the input string is invalid
     */
    public Deadline(String taskString) {
        super(taskString);
        String[] parts = taskString.split(" by ");
        if (parts.length == 2) {
            setTaskName(parts[0]);
            this.deadline = parts[1];
        } else {
            throw new IllegalArgumentException("Invalid deadline task format.");
        }
    }

    /**
     * Returns the string representation of the deadline task.
     * Includes the task type, completion status, name, and deadline.
     *
     * @return a formatted string representing the deadline task
     */
    @Override
    public String toString() {
        String state = isDone ? "[X]" : "[ ]";
        return "[D]" + state + " " + taskName + " (by: " + deadline + ")";
    }

    /**
     * Formats the deadline task for file storage.
     * Includes task type, completion status, name, and deadline.
     *
     * @return a string formatted for saving to a file
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "  Done  " : "Not Done") + " | " + getTaskName() + " (by: " + deadline + ")";
    }

    /**
     * Reconstructs a {@code Deadline} task from a formatted file string.
     * The input string should be in the format: {@code "D | <status> | <name> (by: <deadline>)"}.
     *
     * @param fileFormat the string representation of the deadline task from a file
     * @return a new {@code Deadline} object parsed from the file string
     * @throws IllegalArgumentException if the format of the file string is invalid
     */
    public static Task fromFileFormat(String fileFormat) {
        String[] parts = fileFormat.split(" \\(by: ");
        String time = parts[1].substring(0, parts[1].length() - 1); // Remove closing parenthesis
        String deadline = parts[0] + " by " + time;
        return new Deadline(deadline);
    }
}

