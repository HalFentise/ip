package Task;

import java.util.ArrayList;
import Exceptions.*;

/**
 * A utility class for managing and interacting with a list of tasks.
 * Provides methods to add, delete, retrieve, and mark tasks as completed or uncompleted.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list initialized with the given list of tasks.
     *
     * @param tasks the list of tasks to initialize the task list with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index the index of the task to be deleted
     * @throws TaskException if the index is out of range
     */
    public void deleteTask(int index) throws TaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskException("Task index is out of range.");
        }
        tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index from the task list.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     * @throws TaskException if the index is out of range
     */
    public Task getTask(int index) throws TaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskException("Task index is out of range.");
        }
        return tasks.get(index);
    }

    /**
     * Retrieves the list of all tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param index the index of the task to mark as completed
     * @throws TaskException if the index is out of range
     */
    public void markTask(int index) throws TaskException {
        getTask(index).mark();
    }

    /**
     * Marks the task at the specified index as not completed.
     *
     * @param index the index of the task to mark as not completed
     * @throws TaskException if the index is out of range
     */
    public void unmarkTask(int index) throws TaskException {
        getTask(index).unmark();
    }
}
