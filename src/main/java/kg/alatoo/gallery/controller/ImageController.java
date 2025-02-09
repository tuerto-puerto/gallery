package kg.alatoo.gallery.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class ImageController {

    @FXML
    private ImageView imageView;

    @FXML
    private Label imageTitle;

    @FXML
    private VBox imageContainer;

    private double zoomFactor = 1.0; // Tracks the zoom level
    private Stage stage;

    public void setImageData(File imageFile) {
        imageView.setImage(new Image("file:" + imageFile.getAbsolutePath()));
        imageTitle.setText(imageFile.getName());
        imageView.setFitWidth(800); // Default size to fit
        imageView.setFitHeight(600); // Default size to fit
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void zoomIn() {
        zoomFactor += 0.1;
        applyZoom();
    }

    @FXML
    private void zoomOut() {
        if (zoomFactor > 0.2) { // Prevent shrinking too much
            zoomFactor -= 0.1;
            applyZoom();
        }
    }

    private void applyZoom() {
        imageView.setFitWidth(imageView.getImage().getWidth() * zoomFactor);
        imageView.setFitHeight(imageView.getImage().getHeight() * zoomFactor);
    }

    @FXML
    private void goBack() {
        if (stage != null) {
            stage.close();
        }
    }
}
