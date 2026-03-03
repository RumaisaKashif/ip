package pook;

import javafx.util.Pair;

/**
 * Handles input parsing and execution based on user commands
 */
public class Parser {
    // Used ChatGPT to come up with strings that can give the chatbot a personality
    // similar to Garfield

    /**
     * Indicates if the program should continue to take input and executes commands
     *
     * @param userInput user input command
     * @param tasks     TaskList to modify
     * @param storage   to save and/or get tasks
     * @param notes     Notes list to modify
     * @param noteStorage to save and/or get notes
     * @return chatbot's response as a Pair of boolean (should continue?)
     *         and response string
     * @throws PookException if the command is invalid
     */
    public static Pair<Boolean, String> handleInput(TaskList tasks, String userInput, Storage storage, NoteList notes,
            NoteStorage noteStorage)
            throws PookException {
        assert tasks != null : "TaskList should not be empty";
        assert userInput != null : "User input should not be empty";
        assert storage != null : "Storage should not be undefined";
        assert noteStorage != null : "NoteStorage should not be undefined";
        assert notes != null : "NoteList should not be empty";

        String result = "";

        try {
            if (userInput.equals("bye")) {
                return new Pair<Boolean, String>(false,
                        "You're welcome, I don't know what you'd do without me either. Bye!");
            } else if (userInput.startsWith("mark")) {
                result = parseMarkableTask(userInput, tasks, storage);
            } else if (userInput.startsWith("unmark")) {
                result = parseUnmarkableTask(userInput, tasks, storage);
            } else if (userInput.startsWith("find")) {
                result = parseFilterableTasks(userInput, tasks);
            } else if (userInput.startsWith("delete")) {
                result = parseRemovableTasks(userInput, tasks, storage);
            } else if (userInput.startsWith("todo")) {
                result = parseTodo(userInput, tasks, storage);
            } else if (userInput.startsWith("deadline")) {
                result = parseDeadline(userInput, tasks, storage);
            } else if (userInput.startsWith("event")) {
                result = parseEvent(userInput, tasks, storage);
            } else if (userInput.equals("list")) {
                result = tasks.getTaskList();
            } else if (userInput.startsWith("note")) {
                result = parseNote(userInput, notes, noteStorage);
            } else {
                throw new PookException("I didn't get that, please reenter a valid command.");
            }
        } catch (PookException | IndexOutOfBoundsException e) {
            result = e.getMessage();
        }

        return new Pair<Boolean, String>(true, result);
    }

    private static String parseMarkableTask(String userInput, TaskList tasks, Storage storage) throws PookException {
        String[] segments = userInput.split(" ");

        if (segments.length < 2) {
            throw new PookException("Oh boy. Please specify which list item needs to be marked.");
        }

        int taskIndex;

        try {
            taskIndex = Integer.parseInt(segments[1]) - 1;
        } catch (NumberFormatException e) {
            throw new PookException("That doesn’t look like a valid task number.");
        }

        if (taskIndex >= tasks.getList().size() || taskIndex < 0) {
            throw new PookException("Invalid task number!");
        }

        Task markableTask = tasks.getTask(taskIndex);

        if (markableTask.getCompletionStatus()) {
            throw new PookException("I've already marked this task as done!");
        }

        markableTask.setStatus(true);
        storage.saveFile(tasks.getList());

        return "Wow. Productivity? Sounds boring.\n" + "Anyways, I've marked this as done:\n\n " + markableTask;
    }

    private static String parseUnmarkableTask(String userInput, TaskList tasks, Storage storage) throws PookException {
        String[] segments = userInput.split(" ");

        if (segments.length < 2) {
            throw new PookException("Please specify which list item needs to be unmarked.");
        }

        int taskIndex;

        try {
            taskIndex = Integer.parseInt(segments[1]) - 1;
        } catch (NumberFormatException e) {
            throw new PookException("That doesn’t look like a valid task number.");
        }

        if (taskIndex >= tasks.getList().size() || taskIndex < 0) {
            throw new PookException("Invalid task number!");
        }

        Task unmarkableTask = tasks.getTask(taskIndex);

        if (!unmarkableTask.getCompletionStatus()) {
            throw new PookException("I've already unmarked this task!");
        }

        unmarkableTask.setStatus(false);
        storage.saveFile(tasks.getList());

        return "Oh joy, humans love redoing pointless work.\n"
                + "Fine. It’s unmarked:\n\n"
                + unmarkableTask
                + "\nI’m going back to bed.";
    }

    private static String parseFilterableTasks(String userInput, TaskList tasks) throws PookException {
        String[] segments = userInput.split(" ");

        if (segments.length < 2) {
            throw new PookException("Please specify the keyword you're looking for.");
        }

        String keyword = segments[1];
        TaskList filteredTasks = tasks.filterTasks(keyword);

        return filteredTasks.getTaskMatches();
    }

    private static String parseRemovableTasks(String userInput, TaskList tasks, Storage storage) throws PookException {
        String[] segments = userInput.split(" ");

        if (segments.length < 2) {
            throw new PookException("Please specify which list item needs to be deleted.");
        }

        int taskIndex;

        try {
            taskIndex = Integer.parseInt(segments[1]) - 1;
        } catch (NumberFormatException e) {
            throw new PookException("That doesn’t look like a valid number.");
        }

        if (taskIndex >= tasks.getList().size() || taskIndex < 0) {
            throw new PookException("Invalid task number!");
        }

        Task deletableTask = tasks.remove(taskIndex);
        storage.saveFile(tasks.getList());

        return "Poof. It's gone. Like my will to live on Mondays.\n\n "
                + deletableTask
                + "\nNow you have "
                + tasks.getList().size()
                + " tasks left. Try not to add more.";
    }

    private static String parseTodo(String userInput, TaskList tasks, Storage storage) throws PookException {
        String[] segments = userInput.split(" ");

        if (segments.length < 2 || segments[1].trim().isEmpty()) {
            throw new PookException("Please specify a description for this todo task.");
        }

        Task task = new Todo(segments[1]);
        tasks.add(task);
        storage.saveFile(tasks.getList());

        return "You want to do *that*? Bold choice.\n"
                + "Fine. I added it:\n\n "
                + task
                + "\nYou now have "
                + tasks.getList().size()
                + " tasks. I recommend a nap instead.";
    }

    private static String parseDeadline(String userInput, TaskList tasks, Storage storage) throws PookException {
        String[] segments = userInput.substring(9).split(" /by ");

        if (segments.length < 2 || segments[0].trim().isEmpty() || segments[1].trim().isEmpty()) {
            throw new PookException("Please specify a description and due date for this deadline.");
        }

        Task task = new Deadline(segments[0], segments[1]);
        tasks.add(task);
        storage.saveFile(tasks.getList());

        return "Deadline? Ugh… hopefully it doesn’t involve the vet or sunlight. I've added it:\n\n "
                + task
                + "\nNow you have "
                + tasks.getList().size()
                + " tasks in the list. Could never be me.";
    }

    private static String parseEvent(String userInput, TaskList tasks, Storage storage) throws PookException {
        if (userInput.length() < 7) {
            throw new PookException("Please specify a description, start time, and end time for this event.");
        }

        String[] segments = userInput.substring(6).split(" /from | /to ");

        if (segments.length < 3 || segments[0].trim().isEmpty() || segments[1].trim().isEmpty()
                || segments[2].trim().isEmpty()) {
            throw new PookException("Please specify a desription, start time, and end time for this event.");
        }

        Task task = new Event(segments[0], segments[1], segments[2]);
        tasks.add(task);
        storage.saveFile(tasks.getList());

        return "An event? Does it involve food?\n"
                + "Added:\n\n "
                + task
                + "\nNow you have "
                + tasks.getList().size()
                + " things to attend. Exhausting.";
    }

    private static String parseNote(String userInput, NoteList notes, NoteStorage storage) throws PookException {
        String[] segments = userInput.split(" ", 3);

        if (segments.length < 2) {
            throw new PookException("Invalid note command. Try note add/delete/list index.");
        }

        switch (segments[1]) {
        case "add":
            if (segments.length < 3) {
                throw new PookException("Please provide note content.");
            }
            return addNote(segments[2], notes, storage);
        case "delete":
            if (segments.length < 3) {
                throw new PookException("Specify note number to delete.");
            }
            return deleteNote(segments[2], notes, storage);
        case "list":
            return notes.getNoteList();
        default:
            throw new PookException("Unknown note command.");
        }
    }

    private static String addNote(String note, NoteList notes, NoteStorage noteStorage) {
        notes.add(new Note(note));
        noteStorage.saveFile(notes.getList());
        return "Added note to the Garfield archive,"
                + "which mostly collects snacks and sarcasm but we make exceptions.\n\n"
                + note;
    }

    private static String deleteNote(String note, NoteList notes, NoteStorage noteStorage) throws PookException {
        int index;

        try {
            index = Integer.parseInt(note) - 1;
        } catch (NumberFormatException e) {
            throw new PookException("That doesn’t look like a valid number.");
        }

        if (index >= notes.getList().size() || index < 0) {
            throw new PookException("Invalid note number!");
        }

        Note removed = notes.remove(index);
        noteStorage.saveFile(notes.getList());
        return "Deleted it. Good, more time for TV and lasagna:\n\n" + removed;
    }
}
