public class Deadline extends Task{
    private String deadline;

    Deadline(String taskName,String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public String toString() {
        String state = isDone ? "[X]" : "[ ]";
        return "[D]" + state + " " + taskName + "(by:" + deadline + ")";
    }
}
