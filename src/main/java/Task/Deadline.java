package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String deadlineTime;
    private LocalDate date = null;
    private LocalDateTime dateTime = null;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadlineTime = deadline;
        parseInput(deadline);
    }

    private void parseInput(String string) {
        //define time format
        String[] dateTimeFormats = {
                "d/M/yyyy HHmm",
                "d/M/yyyy",
        };

        for (String format : dateTimeFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                if (format.contains("HHmm")) { //if contain time
                    this.dateTime = LocalDateTime.parse(string, formatter);
                } else { //only contain date
                    this.date = LocalDate.parse(string, formatter);
                }
                return;
            } catch (DateTimeParseException e) {
                //ignore exception
            }
        }
    }

    @Override
    public String toString() {
        if (dateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            return "[D]" + super.toString() + " (by: " + dateTime.format(formatter) + ")";
        } else if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return "[D]" + super.toString() + " (by: " + date.format(formatter) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + deadlineTime + ")";
        }
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "  Done  " : "Not Done") + " | " + getTaskName() + " (by: " + deadlineTime + ")";
    }

    static public Task fromFileFormat(String fileFormat) {
        String[] parts = fileFormat.split(" \\(by: ");
        String time = parts[1].substring(0, parts[1].length() - 1);
        return new Deadline(parts[0], time);
    }
}
