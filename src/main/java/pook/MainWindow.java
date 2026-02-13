package pook;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.util.Pair;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Pook pook;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/odie.png"));
    private Image pookImage = new Image(this.getClass().getResourceAsStream("/images/garfield.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the chatbot instance */
    public void setPook(Pook p) {
        pook = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Closes the app after reading "bye".
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        Pair<Boolean, String> response = pook.getResponse(userInput.getText());
        // String pookText = pook.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getPookDialog(response.getValue(), pookImage)
        );
        userInput.clear();
        /* 
        Close GUI if user typed "bye"
        Took help from ChatGPT to figure out how to close the app AFTER showing the message
        */ 
        if (!response.getKey()) { 
            PauseTransition delay = new PauseTransition(Duration.millis(1000));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        }
    }
}
