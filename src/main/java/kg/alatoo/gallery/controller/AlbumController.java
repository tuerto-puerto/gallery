package kg.alatoo.gallery.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kg.alatoo.gallery.loader.LoaderThread;

import java.io.File;
import java.io.IOException;

public class AlbumController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label albumTitle;

    @FXML
    private TilePane mediaGrid;

    private LoaderThread loaderThread;

    public void setAlbum(String albumName, File[] mediaFiles) {
        mediaGrid.setAlignment(Pos.CENTER);
        this.albumTitle.setText(albumName);
        mediaGrid.getChildren().clear();
        loaderThread = new LoaderThread(mediaFiles, mediaGrid, this);
        Thread thread = new Thread(loaderThread);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    public void onBackButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/kg/alatoo/gallery/gallery-view.fxml"));
            Parent galleryRoot = loader.load();
            Stage stage = (Stage) mediaGrid.getScene().getWindow();
            stage.getScene().setRoot(galleryRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openImageView(File imageFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/kg/alatoo/gallery/image-view.fxml"));
            Parent imageViewRoot = loader.load();

            ImageController controller = loader.getController();
            controller.setImageData(imageFile);

            Stage imageStage = new Stage();
            imageStage.setTitle("Image Viewer");
            imageStage.setScene(new javafx.scene.Scene(imageViewRoot));
            controller.setStage(imageStage);

            imageStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
