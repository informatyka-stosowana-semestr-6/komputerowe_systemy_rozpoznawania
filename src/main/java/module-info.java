module com.example.knn_gui {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                requires org.kordamp.ikonli.javafx;
    requires com.google.gson;

    opens com.example.knn_gui to javafx.fxml;
    exports com.example.knn_gui;
}