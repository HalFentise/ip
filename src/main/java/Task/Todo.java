package Task;

/**
 * Represents a basic task without a specific deadline or time.
 * Extends the {@code Task} class to implement a simple "To-Do" task.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} task with the specified task name.
     *
     * @param taskName the name or description of the task
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns the string representation of the to-do task.
     * Includes the task type, completion status, and task name.
     *
     * @return a formatted string representing the to-do task
     */
    @Override
    public String toString() {
        String state = isDone ? "[X]" : "[ ]";
        return "[T]" + state + " " + taskName;
    }

    /**
     * Formats the to-do task for file storage.
     * Includes task type, completion status, and task name.
     *
     * @return a string formatted for saving to a file
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "  Done  " : "Not Done") + " | " + getTaskName();
    }

    /**
     * Reconstructs a {@code Todo} task from a formatted file string.
     * The input string should be in the format: {@code "T | <status> | <name>"}.
     *
     * @param fileFormat the string representation of the to-do task from a file
     * @return a new {@code Todo} object parsed from the file string
     */
    static public Task fromFileFormat(String fileFormat) {
        return new Todo(fileFormat);
    }
}

