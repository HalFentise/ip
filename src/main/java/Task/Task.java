package Task;

/**
 * Represents a generic task with a name and completion status.
 * Provides methods to mark, unmark, and format the task for storage or display.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified name.
     * The task is initially not marked as done.
     *
     * @param taskName the name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    /**
     * Returns the string representation of the task.
     * Includes the completion status and task name.
     *
     * @return a formatted string representing the task
     */
    @Override
    public String toString() {
        String state = isDone ? "[X]" : "[ ]";
        return state + " " + taskName;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Checks if the given task name matches this task's name.
     *
     * @param taskName the name to compare with this task's name
     * @return true if the names match, false otherwise
     */
    boolean isSame(String taskName) {
        return this.taskName.equals(taskName);
    }

    /**
     * Retrieves the name of the task.
     *
     * @return the name of the task
     */
    String getTaskName() {
        return taskName;
    }

    /**
     * Updates the name of the task.
     *
     * @param taskName the new name for the task
     */
    protected void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Formats the task for file storage.
     * Includes task type, completion status, and name.
     *
     * @return a string formatted for saving to a file
     */
    public String toFileFormat() {
        return "Task" + (isDone ? "  Done  " : "Not Done") + " | " + getTaskName();
    }

    /**
     * Creates a Task object from a formatted file string.
     *
     * @param fileFormat the string representing the task in file format
     * @return a Task object reconstructed from the file string
     */
    public static Task fromFileFormat(String fileFormat) {
        String[] parts = fileFormat.split(" \\| ", 3);
        boolean done = parts[1].equals("  Done  ");
        Task task;
        System.out.println(parts[1]);

        switch (parts[0]) {
            case "T":
                task = Todo.fromFileFormat(parts[2]);
                break;
            case "D":
                task = Deadline.fromFileFormat(parts[2]);
                break;
            case "E":
                task = Event.fromFileFormat(parts[2]);
                break;
            default:
                task = new Task(parts[0]);
        }

        if (done) {
            task.mark();
        } else {
            task.unmark();
        }
        return task;
    }
}

