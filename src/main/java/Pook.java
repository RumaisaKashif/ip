import java.util.Scanner;

public class Pook {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
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
            System.out.println(user_input);
            System.out.println("----------------------");
            System.out.println();
        }
        input.close();
        System.out.println("----------------------");
    }
}
