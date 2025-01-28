module org.example.restaurantmenuapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires java.sql;

    opens org.example.restaurantmenuapp to javafx.fxml;
    exports org.example.restaurantmenuapp;
}