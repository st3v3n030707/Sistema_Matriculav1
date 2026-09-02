package ni.edu.uam.sistema_matricula;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MatriculaController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
