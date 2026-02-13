package pook;

import java.util.ArrayList;
import java.util.List;

/**
 * Captures a list of saved tasks.
 * Provides methods to add, remove, get, print, and list tasks.
 */
public class TaskList {
    private final List<Task> inputList;

    /**
     * Creates a list of Tasks
     */
    public TaskList() { 
        inputList =  new ArrayList<>();
    }

    public TaskList(List<Task> taskList) { 
        inputList = taskList;
    }

    public List<Task> getList() {
        return this.inputList;
    }

    public void add(Task task) {
        inputList.add(task);
    }

    public Task remove(int index) {
        return inputList.remove(index);
    }

    public Task getTask(int index) {
        return inputList.get(index);
    }

    public String getTaskList() { 
        if (inputList.isEmpty()) {
            return "Your task list is empty.";
        }

        String listString = "";

        for (int i = 0; i < inputList.size(); i++) {
            Task task = inputList.get(i);
            listString = listString + (i + 1) + ". " + task + "\n";
        }

        return listString;
    }

    public String getTaskMatches() {
        if (inputList.isEmpty()) {
            return "No matching tasks found";
        }

        String listString = "Here are the matching tasks in your list:\n";
        
        for (int i = 0; i < inputList.size(); i++) {
            Task task = inputList.get(i);
            listString = listString + (i + 1) + ". " + task + "\n";
        }

        return listString;
    }
    /**
     * Returns a list of all tasks containing a specific keyword
     * 
     * @param keyword
     * @return filtered task list
     */
    public TaskList filterTasks(String keyword) { 
        return new TaskList(inputList.stream().filter(task -> task.contains(keyword)).toList());
    }
}
