package pook;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class NoteStorage {

    private final String path = "data/notes.txt";

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
            System.out.println("Error loading notes.");
        }
    }

    public void saveFile(List<Note> notes) {
        try {
            FileWriter fw = new FileWriter(path);

            for (Note note : notes) {
                fw.write(note.toStorableString());
                fw.write(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving notes.");
        }
    }
}
