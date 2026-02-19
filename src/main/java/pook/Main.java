package pook;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Pook pook = new Pook();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPook(pook);  // inject the Pook instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
