module ni.edu.uam.sistemamatricula {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens ni.edu.uam.sistemamatricula to javafx.fxml;
    opens ni.edu.uam.sistemamatricula.models to javafx.base;

    exports ni.edu.uam.sistemamatricula;
}