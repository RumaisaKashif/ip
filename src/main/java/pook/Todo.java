package pook;
/**
 * Represents tasks containing only a description
 */
public class Todo extends Task { 
    /**
     * Creates a Todo
     * 
     * @param description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStorableString() { 
        return "T | " + getStatusNumber() + " | " + taskDescription;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}