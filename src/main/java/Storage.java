import java.io.*;
import java.util.ArrayList;
import Task.*;

/**
 * Handles storage and retrieval of tasks to and from a file.
 * Provides methods to load tasks from a file and save tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath the path of the file where tasks are stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the list of tasks from the specified file.
     * If the file does not exist, an empty task list is returned.
     *
     * @return an ArrayList of tasks loaded from the file
     * @throws IOException if an error occurs during file reading
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Task.fromFileFormat(line));
            }
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the specified file.
     * Creates the file and parent directories if they do not exist.
     *
     * @param tasks the list of tasks to be saved to the file
     * @throws IOException if an error occurs during file writing
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        }
    }
}



