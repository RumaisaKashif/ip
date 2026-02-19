package pook;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

// Code adapted from provided tutorial

// Used ChatGPT to write Javadoc header and comments for this file

/**
 * Entry point of the Pook application.
 * 
 * Responsible for bootstrapping the JavaFX application and loading the main
 * window from the FXML file.
 */
public class Main extends Application {

    private Pook pook = new Pook();

    /**
     * Starts the JavaFX application.
     * 
     * Loads MainWindow.fxml, initializes the controller,
     * injects the Pook instance, and displays the primary stage.
     *
     * @param stage The primary stage provided by the JavaFX runtime.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPook(pook);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
