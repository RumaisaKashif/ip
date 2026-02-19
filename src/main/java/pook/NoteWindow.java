package pook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents a sticky note modal view
 */
public class NoteWindow extends AnchorPane {
    @FXML
    private TextArea noteArea;

    @FXML
    private Button saveButton;

    private Note note;
    private NoteList notes;
    private NoteStorage noteStorage;

    public NoteWindow(Note newNote, NoteList noteList, NoteStorage storage) {
        this.note = newNote;
        this.notes = noteList;
        this.noteStorage = storage;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/NoteWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        noteArea.setText(note.getContent());

        saveButton.setOnAction(e -> handleSave());
    }

    private void handleSave() {
        note.setContent(noteArea.getText());
        noteStorage.saveFile(notes.getList());

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}
