import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Pook {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> input_list = new ArrayList<>();
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
            }
            else if (user_input.equals("list")) {
                for (int i = 0; i < input_list.size(); i++) {
                    System.out.println(i + 1 + ". " + input_list.get(i));
                } 
                System.out.println("----------------------");
            }
            else {
                input_list.add(user_input);    
                System.out.println("added: " + user_input);
                System.out.println("----------------------");
            }
        }
        input.close();
        System.out.println("----------------------");
    }
}
