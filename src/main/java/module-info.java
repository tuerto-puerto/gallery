module kg.alatoo.gallery {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens kg.alatoo.gallery to javafx.fxml;
    exports kg.alatoo.gallery;
}