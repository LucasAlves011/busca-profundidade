module com.example.guiprofundidade2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.guiprofundidade2 to javafx.fxml;
    exports com.example.guiprofundidade2;
}