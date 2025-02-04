public class Event extends Task{
    private String startTime;
    private String endTime;

    Event(String taskName,String startTime,String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString() {
        String state = isDone ? "[X]" : "[ ]";
        String time = "(from: " + this.startTime + " to: " + this.endTime + ")";
        return "[E]" + state + " " + taskName + " " + time;
    }
}
