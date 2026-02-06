package pook;

import java.util.ArrayList;
import java.util.List;

/**
 * Captures a list of saved tasks.
 * Provides methods to add, remove, get, print, and list tasks.
 */
public class TaskList {
    private final List<Task> inputList;

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

    public void printList() {
        for (int i = 0; i < inputList.size(); i++) {
            Task task = inputList.get(i);
            System.out.println(i + 1 + ". " + task.toString());
        } 
        System.out.println("----------------------");
    }

    public void printMatches(String display) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < inputList.size(); i++) {
            Task task = inputList.get(i);
            System.out.println(i + 1 + ". " + task.toString());
        } 
        System.out.println("----------------------");
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
