package kg.alatoo.gallery.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class AlbumItemController {

    @FXML
    private ImageView thumbnail;

    @FXML
    private Text albumName;

    @FXML
    private Text mediaInfo;

    public void setAlbumData(String name, Image thumbnailImage, int imageCount, int videoCount) {
        albumName.setText(name);
        thumbnail.setImage(thumbnailImage);
        mediaInfo.setText(imageCount + " Photos, " + videoCount + " Videos");
    }
}
