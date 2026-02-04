package pook;

import java.util.Scanner;
/**
 * Handles all visual cues to the user
 * Prints outputs, displays error messages, and gets user input
 */
public class Ui {
    private final Scanner input;

    public Ui() { 
        input = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("----------------------");
        System.out.println("Hello! I'm Pook");
        System.out.println("What can I do for you?");
        System.out.println("----------------------");
    }

    public String readCommand() {
        return input.nextLine();
    }

    public void showLine() {
        System.out.println("----------------------");
    }

    public void showMessage(String message) {
        System.out.println(message);
        showLine();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
        showLine();
    }

    public void close() {
        input.close();
    }
}
