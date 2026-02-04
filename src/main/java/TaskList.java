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
}
