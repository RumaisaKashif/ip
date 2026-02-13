package pook;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label text;
    @FXML
    private ImageView displayPicture;

    public DialogBox(String s, Image i) {
        // text = new Label(s);
        // displayPicture = new ImageView(i);
        // // Background color code from geekforgeeks 
        // BackgroundFill background_fill = new BackgroundFill(Color.DEEPSKYBLUE, 
        //         new CornerRadii(5), Insets.EMPTY);
        // Background background = new Background(background_fill);
        // text.setBackground(background);
        // text.setPadding(new Insets(10, 15, 10, 15));
        // text.setStyle("-fx-text-fill: black; -fx-font-weight: semi-bold;");

        // //Styling the dialog box
        // text.setWrapText(true);
        // displayPicture.setFitWidth(100.0);
        // displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        text.setText(s);
        displayPicture.setImage(i);

        // this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    // private void changeColor() { 
    //     // Background color code from geekforgeeks 
    //     BackgroundFill background_fill = new BackgroundFill(Color.LIGHTSKYBLUE, 
    //             new CornerRadii(5), Insets.EMPTY);
    //     Background background = new Background(background_fill);
    //     text.setBackground(background);
    //     text.setPadding(new Insets(10, 15, 10, 15));
    //     text.setStyle("-fx-text-fill: black; -fx-font-weight: semi-bold;");
    // }

    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    public static DialogBox getPookDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        // db.changeColor();
        return db;
    }
}

