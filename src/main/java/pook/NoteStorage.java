package pook;
// Code adapted from Storage.java

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * Loads saved notes and saves the updated notes.
 */
public class NoteStorage {

    private final String path = "data/notes.txt";
    
    /**
     * Adds notes parsed from the file loaded to the task list specified.
     * Prints exception error if loading runs into an error.
     * 
     * @param notes notes list to modify.
     */
    public void loadFile(List<Note> notes) {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                notes.add(new Note(sc.nextLine()));
            }

            sc.close();
        } catch (IOException e) {
            // Used ChatGPT to come up with appropriate error messages
            System.out.println("Error loading notes to file.");
        }
    }

    /**
     * Saves the updated notes list to the file.
     * Prints an error message if saving runs into an error.
     * 
     * @param notes notes list to save into file.
     */
    public void saveFile(List<Note> notes) {
        try {
            FileWriter fw = new FileWriter(path);

            for (Note note : notes) {
                fw.write(note.toStorableString());
                fw.write(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            // Used ChatGPT to come up with appropriate error messages
            System.out.println("Error saving notes to file.");
        }
    }
}
