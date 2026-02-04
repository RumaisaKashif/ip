import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pook {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Task> input_list = new ArrayList<>();
        Storage storage = new Storage();
        storage.loadFile(input_list);
        System.out.println("----------------------");
        System.out.println("Hello! I'm Pook");
        System.out.println("What can I do for you?");
        System.out.println("----------------------");

        while (true) {
            System.out.println();
            String user_input = input.nextLine();
            System.out.println("----------------------");
            try {
                if (user_input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println();
                    break;
                } else if (user_input.startsWith("mark")) {
                    String[] segments = user_input.split(" ");
                    if (segments.length < 2) {
                        throw new PookException("Please specify which list item needs to be marked.");
                    }
                    int task_index = Integer.parseInt(segments[1]) - 1;
                    Task markable_task = input_list.get(task_index);
                    if (markable_task.getStatusIcon().equals("X")) {
                        throw new PookException("I've already marked this task as done!");
                    }
                    markable_task.setStatus(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" " + markable_task.toString());
                    storage.saveFile(input_list);
                    System.out.println("----------------------");
                }
                else if (user_input.startsWith("unmark")) {
                    String[] segments = user_input.split(" ");
                    if (segments.length < 2) {
                        throw new PookException("Please specify which list item needs to be unmarked.");
                    }
                    int task_index = Integer.parseInt(segments[1]) - 1;
                    Task unmarkable_task = input_list.get(task_index);
                    if (unmarkable_task.getStatusIcon().equals(" ")) {
                        throw new PookException("I've already unmarked this task!");
                    }
                    unmarkable_task.setStatus(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(" " + unmarkable_task.toString());
                    storage.saveFile(input_list);
                    System.out.println("----------------------");
                } else if (user_input.startsWith("delete")) {
                    String[] segments = user_input.split(" ");
                    if (segments.length < 2) {
                        throw new PookException("Please specify which list item needs to be deleted.");
                    }
                    int task_index = Integer.parseInt(segments[1]) - 1;
                    if (task_index >= input_list.size() || task_index < 0) {
                        throw new PookException("Invalid task number!");
                    }
                    Task deletable_task = input_list.remove(task_index);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(" " + deletable_task.toString());
                    storage.saveFile(input_list);
                    System.out.println("Now you have " + input_list.size() + " tasks in the list.");
                    System.out.println("----------------------");
                } else if (user_input.startsWith("deadline")) {
                    String[] desc = user_input.substring(9).split(" /by ");
                    if (desc.length < 2 || desc[0].trim().isEmpty() || desc[1].trim().isEmpty()) {
                        throw new PookException("Please specify a desription and due date for this deadline.");
                    }
                    Deadline task = new Deadline(desc[0], desc[1]);
                    input_list.add(task);
                    storage.saveFile(input_list);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task.toString());
                    System.out.println("Now you have " + input_list.size() + " tasks in the list.");
                    System.out.println("----------------------");
                } else if (user_input.startsWith("todo")) {
                    String[] segments = user_input.split(" ");
                    if (segments.length < 2 || segments[1].trim().isEmpty()) {
                        throw new PookException("Please specify a desription for this todo task.");
                    }
                    Todo task = new Todo(segments[1]);
                    input_list.add(task);
                    storage.saveFile(input_list);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task.toString());
                    System.out.println("Now you have " + input_list.size() + " tasks in the list.");
                    System.out.println("----------------------");
                } else if (user_input.startsWith("event")) {
                    if (user_input.length() < 7) { 
                        throw new PookException("Please specify a description, start time, and end time for this event.");
                    }
                    String[] segments = user_input.substring(6).split(" /from | /to ");
                    if (segments.length < 3 || segments[0].trim().isEmpty() || segments[1].trim().isEmpty() || segments[2].trim().isEmpty()) {
                        throw new PookException("Please specify a desription, start time, and end time for this event.");
                    }
                    Event task = new Event(segments[0], segments[1], segments[2]);
                    input_list.add(task);
                    storage.saveFile(input_list);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task.toString());
                    System.out.println("Now you have " + input_list.size() + " tasks in the list.");
                    System.out.println("----------------------");
                }
                else if (user_input.equals("list")) {
                    for (int i = 0; i < input_list.size(); i++) {
                        Task task = input_list.get(i);
                        System.out.println(i + 1 + ". " + task.toString());
                    } 
                    System.out.println("----------------------");
                } else if (user_input.isEmpty()) {
                    throw new PookException("Please enter a task.");
                }
            } catch (PookException e) {
                System.out.println(e.getMessage());
                System.out.println("----------------------");
            }
        }

        input.close();
        System.out.println("----------------------");
    }

}