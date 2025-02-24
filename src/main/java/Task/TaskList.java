package Task;

import java.util.ArrayList;
import Exceptions.*;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws TaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskException("Task index is out of range.");
        }
        tasks.remove(index);
    }

    public Task getTask(int index) throws TaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskException("Task index is out of range.");
        }
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markTask(int index) throws TaskException {
        getTask(index).mark();
    }

    public void unmarkTask(int index) throws TaskException {
        getTask(index).unmark();
    }
}

