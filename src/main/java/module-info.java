module com.miguel.proyectojava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires java.xml.bind;
    requires org.apache.commons.codec;
    
    
    opens com.miguel.proyectojava.model to java.xml.bind;
    opens com.miguel.proyectojava.controller to javafx.fxml;
    exports com.miguel.proyectojava;
}