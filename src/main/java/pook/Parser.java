package pook;

/**
 * Handles input parsing and execution based on user commands
 */
public class Parser {

    /**
     * Indicates if the program should continue to take input and executes commands
     *
     * @param userInput user's command
     * @param tasks     TaskList to modify
     * @param storage   to save and/or get tasks
     * @param ui        specified UI
     * @return false if the user enters bye, else returns true
     * @throws PookException if the command is invalid
     */
    public static boolean handleInput(TaskList tasks, String userInput, Storage storage, Ui ui)
            throws PookException {
        try {
            if (userInput.equals("bye")) {
                ui.showMessage("Bye. Hope to see you again soon!");
                return false; 
            } else if (userInput.startsWith("mark")) {
                String[] segments = userInput.split(" ");
                if (segments.length < 2) {
                    throw new PookException("Please specify which list item needs to be marked.");
                }

                int taskIndex = Integer.parseInt(segments[1]) - 1;
                Task markableTask = tasks.getTask(taskIndex);

                if (markableTask.getStatusIcon().equals("X")) {
                    throw new PookException("I've already marked this task as done!");
                }

                markableTask.setStatus(true);
                storage.saveFile(tasks.getList());
                ui.showMessage("Nice! I've marked this task as done:\n " + markableTask);
            } else if (userInput.startsWith("unmark")) {
                String[] segments = userInput.split(" ");
                if (segments.length < 2) {
                    throw new PookException("Please specify which list item needs to be unmarked.");
                }

                int taskIndex = Integer.parseInt(segments[1]) - 1;
                Task unmarkableTask = tasks.getTask(taskIndex);

                if (unmarkableTask.getStatusIcon().equals(" ")) {
                    throw new PookException("I've already unmarked this task!");
                }

                unmarkableTask.setStatus(false);
                storage.saveFile(tasks.getList());
                ui.showMessage("OK, I've marked this task as not done yet:\n " + unmarkableTask);
            } else if (userInput.startsWith("find")) {
                String[] segments = userInput.split(" ");
                if (segments.length < 2) {
                    throw new PookException("Please specify the keyword you're looking for.");
                }

                String keyword = segments[1];
                TaskList filteredTasks = tasks.filterTasks(keyword);
                filteredTasks.printMatches(userInput);
            } else if (userInput.startsWith("delete")) {
                String[] segments = userInput.split(" ");
                if (segments.length < 2) {
                    throw new PookException("Please specify which list item needs to be deleted.");
                }

                int taskIndex = Integer.parseInt(segments[1]) - 1;
                if (taskIndex >= tasks.getList().size() || taskIndex < 0) {
                    throw new PookException("Invalid task number!");
                }
                Task deletableTask = tasks.remove(taskIndex);
                storage.saveFile(tasks.getList());
                ui.showMessage("Noted. I've removed this task:\n " + deletableTask
                        + "\nNow you have " + tasks.getList().size() + " tasks in the list.");
            }

            else if (userInput.startsWith("todo")) {
                String[] segments = userInput.split(" ");
                if (segments.length < 2 || segments[1].trim().isEmpty()) {
                    throw new PookException("Please specify a description for this todo task.");
                }
                Task task = new Todo(segments[1]);
                tasks.add(task);
                storage.saveFile(tasks.getList());
                ui.showMessage("Got it. I've added this task:\n " + task
                        + "\nNow you have " + tasks.getList().size() + " tasks in the list.");
            } else if (userInput.startsWith("deadline")) {
                String[] segments = userInput.substring(9).split(" /by ");
                if (segments.length < 2 || segments[0].trim().isEmpty() || segments[1].trim().isEmpty()) {
                    throw new PookException("Please specify a description and due date for this deadline.");
                }
                Task task = new Deadline(segments[0], segments[1]);
                tasks.add(task);
                storage.saveFile(tasks.getList());
                ui.showMessage("Got it. I've added this task:\n " + task
                        + "\nNow you have " + tasks.getList().size() + " tasks in the list.");
            } else if (userInput.startsWith("event")) {
                if (userInput.length() < 7) { 
                    throw new PookException("Please specify a description, start time, and end time for this event.");
                }
                String[] segments = userInput.substring(6).split(" /from | /to ");
                if (segments.length < 3 || segments[0].trim().isEmpty() || segments[1].trim().isEmpty() || segments[2].trim().isEmpty()) {
                    throw new PookException("Please specify a desription, start time, and end time for this event.");
                }
                Task task = new Event(segments[0], segments[1], segments[2]);
                tasks.add(task);
                storage.saveFile(tasks.getList());
                ui.showMessage("Got it. I've added this task:\n " + task
                        + "\nNow you have " + tasks.getList().size() + " tasks in the list.");
            } else if (userInput.equals("list")) {
                tasks.printList();
            } else if (userInput.isEmpty()) {
                throw new PookException("Please enter a task.");
            } else {
                throw new PookException("I didn't get that, please reenter a valid command.");
            }
        } catch (PookException | IndexOutOfBoundsException e) { 
            ui.showError(e.getMessage());
        }
        return true;
    }
}
