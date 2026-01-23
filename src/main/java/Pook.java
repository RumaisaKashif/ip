import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Pook {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Task> input_list = new ArrayList<>();
        System.out.println("----------------------");
        System.out.println("Hello! I'm Pook");
        System.out.println("What can I do for you?");
        System.out.println("----------------------");
        while (true) {
            System.out.println();
            String user_input = input.nextLine();
            System.out.println("----------------------");
            if (user_input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println();
                break;
            } else if (user_input.startsWith("mark")) {
                String[] segments = user_input.split(" ");
                int task_index = Integer.parseInt(segments[1]) - 1;
                Task markable_task = input_list.get(task_index);
                if (markable_task.getStatusIcon().equals("X")) {
                    System.out.println("I've already marked this task as done!");
                    System.out.println("----------------------");
                    continue;
                }
                markable_task.setStatus(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + markable_task.getTask());
                System.out.println("----------------------");
            }
            else if (user_input.equals("list")) {
                for (int i = 0; i < input_list.size(); i++) {
                    Task task = input_list.get(i);
                    System.out.println(i + 1 + ". " + task.getTask());
                } 
                System.out.println("----------------------");
            } else {
                Task new_task = new Task(user_input);
                input_list.add(new_task);    
                System.out.println("added: " + user_input);
                System.out.println("----------------------");
            }
        }
        input.close();
        System.out.println("----------------------");
    }
}
