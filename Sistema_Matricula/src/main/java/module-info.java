module ni.edu.uam.sistema_matricula {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens ni.edu.uam.sistema_matricula to javafx.fxml;
    opens ni.edu.uam.sistema_matricula.models to javafx.base;

    exports ni.edu.uam.sistema_matricula;
}