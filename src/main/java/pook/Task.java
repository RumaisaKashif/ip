package pook;

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
     * Returns a machine-readable string for file storage.
     *
     * @return storable string e.g. D | 1 | submit | 2018-01-12 1800
     */
    public abstract String toStorableString();

    /**
     * Returns a user-friendly string representation.
     *
     * @return formatted task string e.g. [X] read a book 
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void setStatus(boolean flag) {
        isDone = flag;
    }

    /**
     * Checks for the presence of specified keyword in the task
     * 
     * @param word
     * @return true if the specified is found in the task description, false otherwise
     */
    public boolean contains(String word) { 
        return description.contains(word);
    }
}


