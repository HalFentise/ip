package Task;

public class Event extends Task {
    private String startTime;
    private String endTime;

    Event(String taskName) {
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
        return "E | " + (isDone ? "  Done  " : "Not Done") + " | " + getTaskName();
    }
}
