public class Todo extends Task { 
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStorableString() { 
        return "T | " + getStatusNumber() + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}