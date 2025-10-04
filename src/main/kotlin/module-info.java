module com.example.linkedlistrealisation {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens com.example.linkedlistrealisation to javafx.fxml;
    opens com.example.linkedlistrealisation.controllers;
    exports com.example.linkedlistrealisation;
    exports com.example.linkedlistrealisation.controllers;
}