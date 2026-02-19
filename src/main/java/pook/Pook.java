package pook;

import javafx.util.Pair;

/**
 * Represents the chatbot and facilitates user/chatbot interactions
 */
public class Pook {
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a Chatbot
     * Initialises UI, Storage and TaskList 
     */
    public Pook() {
        storage = new Storage(); 

        try {
            tasks = new TaskList();
            storage.loadFile(tasks.getList());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Reads and executes user commands
     */
    public String run(String input) {
        boolean isAlive = true;

        while (isAlive) {
            try {
                Pair<Boolean, String> result = Parser.handleInput(tasks, input, storage);
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
     * Generates a response for the user's chat message.
     */
    public Pair<Boolean, String> getResponse(String input) {
        try {
            Pair<Boolean, String> result = Parser.handleInput(tasks, input, storage);
            return result;
        } catch (PookException e) {
            return new Pair<>(true, e.getMessage());
        }
    }
}
