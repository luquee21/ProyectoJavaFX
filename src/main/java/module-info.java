module com.miguel.proyectojava {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.miguel.proyectojava.controller to javafx.fxml;
    exports com.miguel.proyectojava;
}