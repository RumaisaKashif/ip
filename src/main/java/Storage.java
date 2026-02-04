import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Loads saved files to return the last task list and saves the updated task list to file.
 */
public class Storage {
    private static final String STORAGE_FILE_PATH = "data/pook.txt"; 

    private File checkFileExists() throws IOException {
        File file = new File(STORAGE_FILE_PATH);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }

        return file;
    }

    /**
     * Adds tasks parsed from the file loaded to the task list specified.
     * Prints exception error if loading runs into an error.
     * 
     * @param tasks task list to modify.
     */
    public void loadFile(List<Task> tasks) { 
        try {
            File file = checkFileExists();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                Task task = parseFile(scanner.nextLine());
                if (task != null) {
                    tasks.add(task);
                }
            }

            scanner.close();
        } catch (IOException e) {
            System.out.print("An error was encountered while loading pook.txt: " + e); 
        }
    }

    private Task parseFile(String line) {
        String[] segments = line.split(" \\| ");
        String taskType = segments[0];
        boolean isDone = segments[1].equals("1");

        switch (taskType) {
            case "T":
                Todo todo = new Todo(segments[2]);
                todo.setStatus(isDone);
                return todo;
            case "D":
                Deadline deadline = new Deadline(segments[2], segments[3]);
                deadline.setStatus(isDone);
                return deadline;
            case "E":
                Event event = new Event(segments[2], segments[3], segments[4]);
                event.setStatus(isDone);
                return event;
            default:
                throw new IllegalArgumentException("An error was encountered while parsing pook.txt");
        }
    }

    /**
     * Saves the updated task list to the file.
     * Prints an error message if saving runs into an error.
     * 
     * @param tasks task list to save into file.
     */
    public void saveFile(List<Task> tasks) { 
        try {
            File file = checkFileExists();
            FileWriter fw = new FileWriter(file);

            for (Task task : tasks) { 
                fw.write(task.toStorableString());
                fw.write(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            System.out.print("An error was encountered while saving pook.txt: " + e);
        }
    }
}
