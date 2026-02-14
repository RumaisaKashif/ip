package pook;
/**
 * Stores task descriptions and status
 * Provides methods to use different string representations of tasks
 */
public abstract class Task {
    protected String taskDescription;
    protected boolean isDone;

    /**
     * Creates a Task with completion status set to false by default
     * 
     * @param description of the task
     */
    public Task(String description) {
        this.taskDescription = description;
        this.isDone = false;
    }
    
    public Boolean getCompletionStatus() { 
        return isDone;
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
        return "[" + getStatusIcon() + "] " + taskDescription;
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
        return taskDescription.contains(word);
    }
}


