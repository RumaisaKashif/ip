package pook;

public class Pook {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
    }

    /**
     * Reads and executes user commands
     */
    public void run() {
        ui.showWelcome();
        boolean isAlive = true;

        while (isAlive) {
            String userInput = ui.readCommand();
            ui.showLine();

            try {
                isAlive = Parser.handleInput(tasks, userInput, storage, ui);
            } catch (PookException e) {
                ui.showError(e.getMessage());
            }
        }
        
        ui.close();
    }

    public static void main(String[] args) {
        new Pook().run();
    }
}
