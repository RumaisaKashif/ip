public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    protected String getStatusNumber() { 
        return (isDone ? "1" : "0");
    }

    /**
     * Transforms Task to a storage suitable string.
     */
    public abstract String toStorableString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void setStatus(boolean flag) {
        isDone = flag;
    }
}


