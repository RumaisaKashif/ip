public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTask() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void setStatus(boolean flag) {
        isDone = flag;
    }
}
