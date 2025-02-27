package Task;

public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
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
        String endTime = timeParts[1].substring(0, timeParts[1].length() - 2);
        return new Event(name, startTime, endTime);
    }
}
