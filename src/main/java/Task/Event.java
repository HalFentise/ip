package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String startTime;
    private String endTime;
    private LocalDate startDate = null;
    private LocalDate endDate = null;
    private LocalDateTime startDateTime = null;
    private LocalDateTime endDateTime = null;

    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
        parseStart(startTime);
        parseEnd(endTime);
    }

    private void parseStart(String string) {
        //define time format
        String[] dateTimeFormats = {
                "d/M/yyyy HHmm",
                "d/M/yyyy",
        };

        for (String format : dateTimeFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                if (format.contains("HHmm")) { //if contain time
                    this.startDateTime = LocalDateTime.parse(string, formatter);
                } else { //only contain date
                    this.startDate = LocalDate.parse(string, formatter);
                }
                return;
            } catch (DateTimeParseException e) {
                //ignore exception
            }
        }
    }

    private void parseEnd(String string) {
        //define time format
        String[] dateTimeFormats = {
                "d/M/yyyy HHmm",
                "d/M/yyyy",
        };

        for (String format : dateTimeFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                if (format.contains("HHmm")) { //if contain time
                    this.endDateTime = LocalDateTime.parse(string, formatter);
                } else { //only contain date
                    this.endDate = LocalDate.parse(string, formatter);
                }
                return;
            } catch (DateTimeParseException e) {
                //ignore exception
            }
        }
    }

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


    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "  Done  " : "Not Done") + " | " + getTaskName() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    static public Task fromFileFormat(String fileFormat) {
        String[] parts = fileFormat.split(" \\(from: ");
        String name = parts[0];
        String[] timeParts = parts[1].split(" to: ");
        String startTime = timeParts[0];
        String endTime = timeParts[1].substring(0, timeParts[1].length() - 1);
        return new Event(name, startTime, endTime);
    }
}
