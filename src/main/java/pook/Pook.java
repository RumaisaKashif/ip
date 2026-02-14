package pook;

import javafx.util.Pair;

/**
 * Represents the chatbot and facilitates user/chatbot interactions
 */
public class Pook {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * Creates a Chatbot
     * Initialises UI, Storage and TaskList 
     */
    public Pook() {
        ui = new Ui();
        storage = new Storage(); 
        try {
            tasks = new TaskList();
            storage.loadFile(tasks.getList());
        } catch (Exception e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
        assert ui != null : "UI should be initialized";
        assert storage != null : "Storage should be initialized";
        assert tasks != null : "TaskList should be initialized";

    }

    /**
     * Reads and executes user commands
     */
    public String run(String input) {
        boolean isAlive = true;

        while (isAlive) {
            assert input != null : "User input should not be empty";
            try {
                Pair<Boolean, String> result = Parser.handleInput(tasks, input, storage);
                assert result != null : "Parser should return a pair";
                isAlive = result.getKey();  
                String response = result.getValue();
                return response;
            } catch (PookException e) {
                return e.getMessage();
            }
        }
        return "";
    }

    /**
     * Starts the program
     */
    // public static void main(String[] args) {
    //     new Pook().run();
    // }

    /**
     * Generates a response for the user's chat message.
     */
    public Pair<Boolean, String> getResponse(String input) {
        // return run(input);
        try {
            Pair<Boolean, String> result = Parser.handleInput(tasks, input, storage);
            return result;
        } catch (PookException e) {
            return new Pair<>(true, e.getMessage());
        }
    }
}
