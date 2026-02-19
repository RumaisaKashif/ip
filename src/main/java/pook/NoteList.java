package pook;

import java.util.ArrayList;
import java.util.List;

public class NoteList {

    private final List<Note> notes;

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

        String result = "";
        for (int i = 0; i < notes.size(); i++) {
            result += (i + 1) + ". " + notes.get(i) + "\n";
        }
        return result;
    }

    /**
     * Returns a list of all notes containing a specific keyword
     * 
     * @param keyword
     * @return filtered task list
     */
    // public NoteList filterTasks(String keyword) { 
    //     return new NoteList(notes.stream().filter(note -> note.contains(keyword)).toList());
    // }
}
