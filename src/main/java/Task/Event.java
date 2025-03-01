package Task;

public class Event extends Task {
    private String startTime;
    private String endTime;

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

    public String toString() {
        String state = isDone ? "[X]" : "[ ]";
        String time = "(from: " + this.startTime + " to: " + this.endTime + ")";
        return "[E]" + state + " " + taskName + " " + time;
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "  Done  " : "Not Done") + " | " + getTaskName() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    static public Task fromFileFormat(String fileFormat) {
        String[] parts = fileFormat.split(" \\(from: ");
        String name = parts[0];
        String[] timeParts = parts[1].split(" to: ");
        String startTime = timeParts[0];
        String endTime = timeParts[1].substring(0, timeParts[1].length() - 1);
        String taskName = name + " from " + startTime + " to " + endTime;
        return new Event(taskName);
    }
}
