package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event with a specific start and end time.
 * This class extends the {@code Task} class and handles event-specific
 * parsing and formatting for both date and time.
 */
public class Event extends Task {
    private String startTime;
    private String endTime;
    private LocalDate startDate = null;
    private LocalDate endDate = null;
    private LocalDateTime startDateTime = null;
    private LocalDateTime endDateTime = null;

    /**
     * Constructs a new {@code Event} object with the specified task name, start time, and end time.
     * The times are parsed and stored as either {@code LocalDate} or {@code LocalDateTime} objects.
     *
     * @param taskName the name or description of the event
     * @param startTime the start time of the event in string format
     * @param endTime the end time of the event in string format
     */
    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
        parseStart(startTime);
        parseEnd(endTime);
    }

    /**
     * Parses the start time of the event into either a {@code LocalDate} or {@code LocalDateTime}.
     * Tries multiple date/time formats to successfully parse the string.
     *
     * @param string the start time as a string
     */
    private void parseStart(String string) {
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

        for (String format : dateTimeFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                if (format.contains("HHmm")) { // if format contains time
                    this.startDateTime = LocalDateTime.parse(string, formatter);
                } else { // only contains date
                    this.startDate = LocalDate.parse(string, formatter);
                }
                return;
            } catch (DateTimeParseException e) {
                // ignore exception if the format does not match
            }
        }
    }

    /**
     * Parses the end time of the event into either a {@code LocalDate} or {@code LocalDateTime}.
     * Tries multiple date/time formats to successfully parse the string.
     *
     * @param string the end time as a string
     */
    private void parseEnd(String string) {
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

        for (String format : dateTimeFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                if (format.contains("HHmm")) { // if format contains time
                    this.endDateTime = LocalDateTime.parse(string, formatter);
                } else { // only contains date
                    this.endDate = LocalDate.parse(string, formatter);
                }
                return;
            } catch (DateTimeParseException e) {
                // ignore exception if the format does not match
            }
        }
    }

    /**
     * Returns the string representation of the event, including the formatted start and end times.
     * If date and time are provided, they will be formatted accordingly.
     *
     * @return a formatted string representing the event
     */
    @Override
    public String toString() {
        String formattedStartTime = (startDateTime != null)
                ? startDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"))
                : (startDate != null)
                ? startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                : startTime;

        String formattedEndTime = (endDateTime != null)
                ? endDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"))
                : (endDate != null)
                ? endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                : endTime;

        return "[E]" + super.toString() + " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
    }

    /**
     * Formats the event for file storage. This includes the event type, completion status,
     * task name, and the formatted start and end times.
     *
     * @return a string formatted for saving to a file
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "  Done  " : "Not Done") + " | " + getTaskName() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    /**
     * Reconstructs an {@code Event} task from a formatted file string.
     * The input string should be in the format: {@code "E | <status> | <name> (from: <startTime> to: <endTime>)"}.
     *
     * @param fileFormat the string representation of the event task from a file
     * @return a new {@code Event} object parsed from the file string
     */
    static public Task fromFileFormat(String fileFormat) {
        String[] parts = fileFormat.split(" \\(from: ");
        String name = parts[0];
        String[] timeParts = parts[1].split(" to: ");
        String startTime = timeParts[0];
        String endTime = timeParts[1].substring(0, timeParts[1].length() - 1);
        return new Event(name, startTime, endTime);
    }
}
