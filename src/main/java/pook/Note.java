package pook;
// Adapted code from Task.java

/**
 * Stores small amounts of textual information added by users
 */
public class Note {

    private String content;

    /**
     * Creates a Note with some user entered content
     *
     * @param content of the note
     */
    public Note(String content) {
        this.content = content;
        assert content != null : "Note content should not be null";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toStorableString() {
        return content;
    }

    /**
     * Returns a user-friendly string representation of the note
     *
     * @return note as a string to display on screen
     */
    @Override
    public String toString() {
        return content;
    }

    public boolean contains(String keyword) {
        return content.contains(keyword);
    }
}
