package pook;
/**
 * Stores small amounts of textual information added by users
 */
public class Note {

    private String content;

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

    @Override
    public String toString() {
        return content;
    }

    public boolean contains(String keyword) {
        return content.contains(keyword);
    }
}
