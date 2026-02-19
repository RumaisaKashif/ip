package pook;
// Adapted code from TaskList.java

import java.util.ArrayList;
import java.util.List;

/**
 * Captures a list of saved notes.
 * Provides methods to add, remove, get, print, and list notes.
 */
public class NoteList {

    private final List<Note> notes;

    /**
     * Creates a list of Notes
     */
    public NoteList() {
        notes = new ArrayList<>();
    }

    public NoteList(List<Note> noteList) { 
        notes = noteList;
    }


    public void add(Note note) {
        notes.add(note);
    }

    public Note remove(int index) {
        return notes.remove(index);
    }

    public Note get(int index) {
        return notes.get(index);
    }

    public List<Note> getList() {
        return notes;
    }

    public String getNoteList() {
        if (notes.isEmpty()) {
            return "Your note list is empty";
        }
        // Used ChatGPT to come up with the string below for the sake of adding a personality to the chatbot
        String result = "Here’s what you wrote… I hope none of it involves exercise.\n";
        for (int i = 0; i < notes.size(); i++) {
            result += (i + 1) + ". " + notes.get(i) + "\n";
        }
        return result;
    }
}
