package kg.alatoo.gallery.loader;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import kg.alatoo.gallery.controller.AlbumController;

import java.io.File;

public class LoaderThread implements Runnable {
    private File[] mediaFiles;
    private TilePane mediaGrid;
    private AlbumController albumController; // Pass the existing AlbumController

    public LoaderThread(File[] mediaFiles, TilePane mediaGrid, AlbumController albumController) {
        this.mediaFiles = mediaFiles;
        this.mediaGrid = mediaGrid;
        this.albumController = albumController;
    }

    @Override
    public void run() {
        for (File file : mediaFiles) {
            if (file.getName().endsWith(".jpg") || file.getName().endsWith(".png") || file.getName().endsWith(".jpeg")) {
                Node placeholder = createPlaceholder(file);
                // Ensure the UI updates occur on the JavaFX Application Thread
                Platform.runLater(() -> mediaGrid.getChildren().add(placeholder));
            }
        }
    }

    private Node createPlaceholder(File file) {
        VBox placeholder = new VBox();
        placeholder.setPrefWidth(200);
        placeholder.setPrefHeight(200);
        placeholder.setStyle("-fx-border-color: gray; -fx-alignment: center;");

        ImageView imageView = new ImageView(new Image("file:" + file.getAbsolutePath(), 200, 200, true, true));
        imageView.setPreserveRatio(true);

        // Handle image click to open the image view
        imageView.setOnMouseClicked(event -> Platform.runLater(() -> {
            if (albumController != null) {
                albumController.openImageView(file);
            }
        }));

        placeholder.getChildren().add(imageView);

        return placeholder;
    }
}
