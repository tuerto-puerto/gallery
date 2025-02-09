module kg.alatoo.gallery {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires net.coobird.thumbnailator;

    opens kg.alatoo.gallery to javafx.fxml;
    exports kg.alatoo.gallery;
    exports kg.alatoo.gallery.controller;
    opens kg.alatoo.gallery.controller to javafx.fxml;
}