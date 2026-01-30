import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter STORAGE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    /* Supports 6 types of date time input string formats to parse */
    private static final DateTimeFormatter[] INPUT_FORMATS = {
        DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),     
        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"), 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),   
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),   
        DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm"), 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")    
    };

    /**
     * Creates a Deadline
     *
     * @param description description of task
     * @param by due date
     * @throws DateTimeParseException if input format is invalid
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        for (DateTimeFormatter f : INPUT_FORMATS) {
            try {
                return LocalDateTime.parse(dateTimeString, f);
            } catch (DateTimeParseException ignored) {

            }
        }
        
        throw new DateTimeParseException(
                "Invalid date format",
                dateTimeString,
                0
        );
    }

    /**
     * Returns a machine-readable string for file storage.
     *
     * @return storable string
     */
    @Override
    public String toStorableString() {
        return "D | " + getStatusNumber() + " | " + description + " | "
                + by.format(STORAGE_FORMAT);
    }

    /**
     * Returns a user-friendly string representation.
     *
     * @return formatted task string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
