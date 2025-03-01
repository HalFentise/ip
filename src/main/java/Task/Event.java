package Task;

/**
 * Represents an event task with a specific start and end time.
 * Extends the {@code Task} class to include additional time-related attributes.
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Constructs a new {@code Event} task with the specified task name, start time, and end time.
     * The input task name should be in the format: {@code "<taskName> from <startTime> to <endTime>"}.
     *
     * @param taskName the full description of the event task, including times
     * @throws IllegalArgumentException if the format of the task name or time is invalid
     */
    public Event(String taskName) {
        super(taskName);
        String[] parts = taskName.split(" from ");
        if (parts.length == 2) {
            setTaskName(parts[0]);
            String[] timeParts = parts[1].split(" to ");
            if (timeParts.length == 2) {
                this.startTime = timeParts[0];
                this.endTime = timeParts[1];
            } else {
                throw new IllegalArgumentException("Invalid event time format.");
            }
        } else {
            throw new IllegalArgumentException("Invalid event task format.");
        }
    }

    /**
     * Returns the string representation of the event task.
     * Includes the task type, completion status, name, and time details.
     *
     * @return a formatted string representing the event task
     */
    @Override
    public String toString() {
        String state = isDone ? "[X]" : "[ ]";
        String time = "(from: " + this.startTime + " to: " + this.endTime + ")";
        return "[E]" + state + " " + taskName + " " + time;
    }

    /**
     * Formats the event task for file storage.
     * Includes task type, completion status, name, and time details.
     *
     * @return a string formatted for saving to a file
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "  Done  " : "Not Done") + " | " + getTaskName() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    /**
     * Reconstructs an {@code Event} task from a formatted file string.
     * The input string should be in the format: {@code "E | <status> | <name> (from: <startTime> to: <endTime>)"}.
     *
     * @param fileFormat the string representation of the event task from a file
     * @return a new {@code Event} object parsed from the file string
     * @throws IllegalArgumentException if the format of the file string is invalid
     */
    public static Task fromFileFormat(String fileFormat) {
        String[] parts = fileFormat.split(" \\(from: ");
        String name = parts[0];
        String[] timeParts = parts[1].split(" to: ");
        String startTime = timeParts[0];
        String endTime = timeParts[1].substring(0, timeParts[1].length() - 1); // Remove closing parenthesis
        String taskName = name + " from " + startTime + " to " + endTime;
        return new Event(taskName);
    }
}

