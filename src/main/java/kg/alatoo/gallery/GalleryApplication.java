package kg.alatoo.gallery;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GalleryApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gallery-view.fxml"));
        primaryStage.setTitle("Gallery HD");
        Scene scene = new Scene(root, 800, 600); // Default size: 800x600
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800); // Minimum width
        primaryStage.setMinHeight(600); // Minimum height
        primaryStage.setMaximized(true); // Starts in fullscreen mode
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}