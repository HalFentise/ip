public class Event extends Task{
    Event(String taskName) {
        super(taskName);
    }

    public String toString() {
        String state = isDone ? "[X]" : "[ ]";
        return "[E]" + state + " " + taskName;
    }
}
