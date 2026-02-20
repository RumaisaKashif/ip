package pook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents tasks containing a description, from date/time, and end date/time
 */
public class Event extends Task {
    // Used some help from ChatGPT to help decide date time formats to help handle
    // them
    /* Supports 6 types of date time input string formats to parse */
    private static final DateTimeFormatter[] INPUT_FORMATS = {
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    };
    private static final DateTimeFormatter OUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    protected LocalDateTime from;
    protected LocalDateTime to;
    /**
     * Creates an Event
     *
     * @param description description of task
     * @param fromDate    from the date/time the event starts
     * @param toDate      till the date/time the event stops
     * @throws PookException if input format is invalid
     */
    public Event(String description, String fromDate, String toDate) throws PookException {
        super(description);
        this.from = parseDateTime(fromDate);
        this.to = parseDateTime(toDate);
    }

    private LocalDateTime parseDateTime(String dateTimeString) throws PookException {
        for (DateTimeFormatter f : INPUT_FORMATS) {
            try {
                return LocalDateTime.parse(dateTimeString, f);
            } catch (DateTimeParseException e) {
                // Try another date time format
            }
        }
        // Error message phrased by ChatGPT
        throw new PookException("Invalid format. Please use a supported format "
                + "like dd/MM/yyyy HHmm or yyyy-MM-dd HH:mm.");
    }

    @Override
    public String toStorableString() {
        return "E | " + getStatusNumber() + " | " + description + " | "
                + from.format(STORAGE_FORMAT) + " | " + to.format(STORAGE_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(OUT_FORMAT)
                + " to: " + this.to.format(OUT_FORMAT) + ")";
    }
}
