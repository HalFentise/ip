package Task;

import java.util.ArrayList;
import Exceptions.*;

/**
 * Represents a list of tasks.
 * This class allows adding, removing, retrieving, and modifying tasks in a collection.
 */
public class TaskList {
    private ArrayList<Task> tasks;  // List that stores the tasks

    /**
     * Default constructor initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor that initializes the task list with an existing list of tasks.
     * @param tasks the list of tasks to initialize the TaskList with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task at a specified index.
     * @param index the index of the task to delete
     * @throws TaskException if the index is out of range
     */
    public void deleteTask(int index) throws TaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskException("Task index is out of range.");
        }
        tasks.remove(index);
    }

    /**
     * Retrieves a task at a specified index.
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
     * Returns the list of all tasks.
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    /**
     * Finds tasks whose names contain the specified keyword.
     * @param keyword the keyword to search for in task names
     * @return a list of tasks that match the search criterion
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> task = new ArrayList<>();
        for (Task keyTask : tasks) {
            if (keyTask.getTaskName().contains(keyword)) {
                task.add(keyTask);
            }
        }
        return task;
    }

    /**
     * Marks the task at the specified index as done.
     * @param index the index of the task to mark as done
     * @throws TaskException if the index is out of range
     */
    public void markTask(int index) throws TaskException {
        getTask(index).mark();
    }

    /**
     * Unmarks the task at the specified index.
     * @param index the index of the task to unmark
     * @throws TaskException if the index is out of range
     */
    public void unmarkTask(int index) throws TaskException {
        getTask(index).unmark();
    }
}
