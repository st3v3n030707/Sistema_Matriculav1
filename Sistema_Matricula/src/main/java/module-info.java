module ni.edu.uam.sistema_matricula {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    // Permite que JavaFX lea la vista principal
    opens ni.edu.uam.sistema_matricula to javafx.fxml;

    // LA LÍNEA CLAVE: Permite que JavaFX lea tu controlador
    opens ni.edu.uam.sistema_matricula.controllers to javafx.fxml;

    // Permite que la tabla lea el modelo
    opens ni.edu.uam.sistema_matricula.models to javafx.base;

    exports ni.edu.uam.sistema_matricula;
}