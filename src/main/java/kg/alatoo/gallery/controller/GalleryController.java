package kg.alatoo.gallery.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GalleryController {
    @FXML
    private GridPane galleryGrid;

    private static final String PICTURES_PATH = System.getProperty("user.home") + "\\Pictures";

    public void initialize() {
        loadAlbums();
    }

    private void loadAlbums() {
        File picturesDir = new File(PICTURES_PATH);

        if (picturesDir.exists() && picturesDir.isDirectory()) {
            File[] folders = picturesDir.listFiles(File::isDirectory);

            if (folders != null) {
                int column = 0;
                int row = 0;

                for (File folder : folders) {
                    File[] imageFiles = folder.listFiles(file -> file.isFile() &&
                            (file.getName().endsWith(".jpg") || file.getName().endsWith(".png")));
                    File[] videoFiles = folder.listFiles(file -> file.isFile() &&
                            (file.getName().endsWith(".mp4") || file.getName().endsWith(".avi") || file.getName().endsWith(".mkv")));

                    int imageCount = (imageFiles != null) ? imageFiles.length : 0;
                    int videoCount = (videoFiles != null) ? videoFiles.length : 0;

                    if (imageCount > 0) {
                        File firstImage = imageFiles[0];
                        try {
                            Node albumNode = createAlbumNode(folder, folder.getName(), firstImage.getAbsolutePath(), imageCount, videoCount);
                            galleryGrid.add(albumNode, column, row);

                            column++;
                            if (column >= 3) { // Adjust columns per row as needed
                                column = 0;
                                row++;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private Node createAlbumNode(File album, String albumName, String imagePath, int imageCount, int videoCount) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/kg/alatoo/gallery/item/album-item.fxml"));
        Node albumTile = loader.load();
        AlbumItemController itemController = loader.getController();

        // Create a low-resolution thumbnail
        Image thumbnailImage = new Image("file:" + imagePath, 150, 150, true, true);
        itemController.setAlbumData(albumName, thumbnailImage, imageCount, videoCount);

        albumTile.setOnMouseClicked(event -> openAlbum(albumName, album));

        return albumTile;
    }

    @FXML
    private void openAlbum(String albumName, File album) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/kg/alatoo/gallery/album-view.fxml"));
            Parent albumRoot = loader.load();

            AlbumController albumController = loader.getController();
            File[] mediaFiles = album.listFiles(); // Assuming album is the folder path
            albumController.setAlbum(albumName, mediaFiles);

            Stage stage = (Stage) galleryGrid.getScene().getWindow();
            stage.getScene().setRoot(albumRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
