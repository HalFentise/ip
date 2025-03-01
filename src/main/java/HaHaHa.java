import Task.*;
import java.io.*;

/**
 * The main class for the HaHaHa application.
 * This application manages a list of tasks with functionalities such as adding, deleting, and marking tasks.
 * It handles user input and interacts with the TaskList, Storage, and Ui components.
 */
public class HaHaHa {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a HaHaHa instance, initializing the user interface, task storage, and task list.
     * If the task data file cannot be loaded, an empty task list is initialized instead.
     *
     * @param filePath the file path to load and store task data
     */
    public HaHaHa(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Starts the main loop of the application.
     * Displays a welcome message and continuously processes user input until the user decides to exit.
     */
    public void run() {
        ui.welcome();
        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.getUserInput(); // Get user input
            Parser.parse(userInput, taskList, ui, storage); // Handle input
        }
    }

    /**
     * The main entry point of the application.
     * Initializes and runs the HaHaHa application with a default file path for task data.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new HaHaHa("./data/list.txt").run();
    }
}


