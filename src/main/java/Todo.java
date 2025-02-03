public class Todo extends Task{
    Todo(String taskName) {
        super(taskName);
    }

    public String toString() {
        String state = isDone ? "[X]" : "[ ]";
        return "[T]" + state + " " + taskName;
    }
}
