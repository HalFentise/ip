package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that has a deadline.
 * This class parses and stores a deadline, which can either be just a date or a date with a specific time.
 */
public class Deadline extends Task {
    private String deadlineTime;  // Stores the deadline time as a string
    private LocalDate date = null;  // Stores deadline as a LocalDate (only date, no time)
    private LocalDateTime dateTime = null;  // Stores deadline as a LocalDateTime (date + time)

    /**
     * Constructor for creating a Deadline task.
     * @param taskName the name of the task
     * @param deadline the deadline string (either date or date + time)
     */
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadlineTime = deadline;
        parseInput(deadline);  // Parses the provided deadline string into a date or datetime
    }

    /**
     * Attempts to parse the input string into either a date or date-time.
     * This method checks multiple date-time formats and tries to match the string to one of them.
     * @param string the string representation of the deadline
     */
    private void parseInput(String string) {
        // Define possible date-time formats
        String[] dateTimeFormats = {
                "dd/MM/yyyy HHmm",  // Format with time
                "dd-MM-yyyy HHmm",
                "yyyy-MM-dd HHmm",
                "yyyy/MM/dd HHmm",
                "dd/MM/yyyy",   // Format without time
                "dd-MM-yyyy",
                "yyyy/MM/dd",
                "yyyy-MM-dd",
        };

        // Try each format and parse the string
        for (String format : dateTimeFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                if (format.contains("HHmm")) {  // If format contains time (HHmm)
                    this.dateTime = LocalDateTime.parse(string, formatter);
                } else {  // If it only contains the date
                    this.date = LocalDate.parse(string, formatter);
                }
                return;  // If parsing is successful, exit the method
            } catch (DateTimeParseException e) {
                // Ignore exception and try the next format
            }
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     * The string includes the task name and its deadline in a readable format.
     * If the deadline includes time, it formats the time as well.
     * @return a string representation of the Deadline task
     */
    @Override
    public String toString() {
        // If the deadline has both date and time
        if (dateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            return "[D]" + super.toString() + " (by: " + dateTime.format(formatter) + ")";
        }
        // If the deadline only has a date
        else if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return "[D]" + super.toString() + " (by: " + date.format(formatter) + ")";
        }
        // If the deadline is just a string
        else {
            return "[D]" + super.toString() + " (by: " + deadlineTime + ")";
        }
    }

    /**
     * Converts the Deadline task into a format suitable for saving to a file.
     * @return a string representation of the Deadline task in file format
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "  Done  " : "Not Done") + " | " + getTaskName() + " (by: " + deadlineTime + ")";
    }

    /**
     * Creates a Deadline task from a file format string.
     * The string is expected to be in the format "D | status | taskName (by: deadline)".
     * @param fileFormat the file format string
     * @return a Deadline task created from the file format string
     */
    static public Task fromFileFormat(String fileFormat) {
        // Split the file format string to extract the task name and deadline
        String[] parts = fileFormat.split(" \\(by: ");
        String time = parts[1].substring(0, parts[1].length() - 1);  // Extract the deadline time
        return new Deadline(parts[0], time);  // Return a new Deadline task with the extracted information
    }
}

