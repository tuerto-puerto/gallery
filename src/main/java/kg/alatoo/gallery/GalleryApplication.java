package kg.alatoo.gallery;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GalleryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GalleryApplication.class.getResource("gallery-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 940, 620);
        stage.setTitle("Gallery Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}