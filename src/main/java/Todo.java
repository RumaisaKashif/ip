public class Todo extends Task { 
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a machine-readable string for file storage.
     *
     * @return storable string
     */
    @Override
    public String toStorableString() { 
        return "T | " + getStatusNumber() + " | " + description;
    }

    /**
     * Returns a user-friendly string representation.
     *
     * @return formatted task string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}