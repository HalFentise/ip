import Task.*;
import java.io.*;

public class HaHaHa {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    HaHaHa(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    // main loop
    public void run() {
        ui.welcome();
        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.getUserInput(); // get user input
            Parser.parse(userInput, taskList, ui, storage);  // handle input
        }
    }

    // main
    public static void main(String[] args) {
        new HaHaHa("./data/list.txt").run();
    }
}

