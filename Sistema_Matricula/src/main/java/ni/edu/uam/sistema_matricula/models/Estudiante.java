package ni.edu.uam.sistema_matricula.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor
public class Estudiante {
    private String nombres;
    private String apellidos;
    private String usuario;
    private String contrasena;
    private LocalDate fechaNacimiento;
    private String departamento;
    private String curso;
    private String modalidad;
    private String horario;

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}