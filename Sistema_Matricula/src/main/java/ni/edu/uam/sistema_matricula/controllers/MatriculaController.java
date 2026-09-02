package ni.edu.uam.sistema_matricula.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ni.edu.uam.sistema_matricula.models.Estudiante;
import ni.edu.uam.sistema_matricula.utils.AlertaUtil;
import java.time.LocalDate;

public class MatriculaController {

    @FXML private TextField txtNombres, txtApellidos, txtUsuario;
    @FXML private PasswordField txtContrasena;
    @FXML private DatePicker dpFecha;
    @FXML private ComboBox<String> cbDepartamento;
    @FXML private ListView<String> lvCursos;
    @FXML private RadioButton rbPresencial, rbVirtual;
    @FXML private CheckBox chkManana, chkTarde, chkNormas;
    @FXML private TableView<Estudiante> tvEstudiantes;
    @FXML private TableColumn<Estudiante, String> colNombre, colDepto, colCurso, colModalidad, colHorario;
    @FXML private TableColumn<Estudiante, LocalDate> colFecha;

    private ToggleGroup grupoModalidad;
    private ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();
    private Estudiante estudianteEnEdicion = null;

    @FXML
    public void initialize() {
        cbDepartamento.getItems().addAll("Managua", "León", "Granada", "Masaya");
        lvCursos.getItems().addAll("Programación", "Excel", "Redes", "Diseño Gráfico");

        grupoModalidad = new ToggleGroup();
        rbPresencial.setToggleGroup(grupoModalidad);
        rbVirtual.setToggleGroup(grupoModalidad);

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colDepto.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        colCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        colModalidad.setCellValueFactory(new PropertyValueFactory<>("modalidad"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        tvEstudiantes.setItems(listaEstudiantes);

        ContextMenu menu = new ContextMenu();
        MenuItem itemEditar = new MenuItem("Editar");
        MenuItem itemEliminar = new MenuItem("Eliminar");
        itemEditar.setOnAction(e -> cargarDatosEdicion());
        itemEliminar.setOnAction(e -> eliminarRegistro());
        menu.getItems().addAll(itemEditar, itemEliminar);
        tvEstudiantes.setContextMenu(menu);
    }

    @FXML
    public void guardarRegistro(ActionEvent event) {
        if (!validarFormulario()) return;

        String horario = chkManana.isSelected() ? "Mañana" : "Tarde";
        String modalidad = rbPresencial.isSelected() ? "Presencial" : "Virtual";

        if (estudianteEnEdicion == null) {
            Estudiante nuevo = new Estudiante(txtNombres.getText().trim(), txtApellidos.getText().trim(), txtUsuario.getText().trim(),
                    txtContrasena.getText(), dpFecha.getValue(), cbDepartamento.getValue(),
                    lvCursos.getSelectionModel().getSelectedItem(), modalidad, horario);
            listaEstudiantes.add(nuevo);
            AlertaUtil.mostrarInfo("Estudiante registrado exitosamente.");
        } else {
            estudianteEnEdicion.setNombres(txtNombres.getText().trim());
            estudianteEnEdicion.setApellidos(txtApellidos.getText().trim());
            estudianteEnEdicion.setUsuario(txtUsuario.getText().trim());
            estudianteEnEdicion.setContrasena(txtContrasena.getText());
            estudianteEnEdicion.setFechaNacimiento(dpFecha.getValue());
            estudianteEnEdicion.setDepartamento(cbDepartamento.getValue());
            estudianteEnEdicion.setCurso(lvCursos.getSelectionModel().getSelectedItem());
            estudianteEnEdicion.setModalidad(modalidad);
            estudianteEnEdicion.setHorario(horario);
            tvEstudiantes.refresh();
            AlertaUtil.mostrarInfo("Estudiante actualizado.");
        }
        limpiarFormulario();
    }

    @FXML
    public void limpiarAction(ActionEvent event) { limpiarFormulario(); }

    private void limpiarFormulario() {
        txtNombres.clear(); txtApellidos.clear(); txtUsuario.clear(); txtContrasena.clear();
        dpFecha.setValue(null); cbDepartamento.getSelectionModel().clearSelection();
        lvCursos.getSelectionModel().clearSelection();
        rbPresencial.setSelected(false); rbVirtual.setSelected(false);
        chkManana.setSelected(false); chkTarde.setSelected(false); chkNormas.setSelected(false);
        estudianteEnEdicion = null;
    }

    @FXML
    public void eliminarRegistro() {
        Estudiante seleccionado = tvEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaEstudiantes.remove(seleccionado);
            AlertaUtil.mostrarInfo("Registro eliminado.");
        }
    }

    @FXML
    public void onTablaDobleClic(MouseEvent event) {
        if (event.getClickCount() == 2) cargarDatosEdicion();
    }

    private void cargarDatosEdicion() {
        estudianteEnEdicion = tvEstudiantes.getSelectionModel().getSelectedItem();
        if (estudianteEnEdicion != null) {
            txtNombres.setText(estudianteEnEdicion.getNombres());
            txtApellidos.setText(estudianteEnEdicion.getApellidos());
            txtUsuario.setText(estudianteEnEdicion.getUsuario());
            txtContrasena.setText(estudianteEnEdicion.getContrasena());
            dpFecha.setValue(estudianteEnEdicion.getFechaNacimiento());
            cbDepartamento.setValue(estudianteEnEdicion.getDepartamento());
            lvCursos.getSelectionModel().select(estudianteEnEdicion.getCurso());
            if (estudianteEnEdicion.getModalidad().equals("Presencial")) rbPresencial.setSelected(true);
            else rbVirtual.setSelected(true);
            if (estudianteEnEdicion.getHorario().equals("Mañana")) chkManana.setSelected(true);
            else chkTarde.setSelected(true);
        }
    }

    @FXML
    public void onTecladoPresionado(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) guardarRegistro(null);
        else if (event.getCode() == KeyCode.ESCAPE) limpiarFormulario();
    }

    private boolean validarFormulario() {
        if (txtNombres.getText().trim().isEmpty() || txtApellidos.getText().trim().isEmpty()) {
            AlertaUtil.mostrarError("Nombres y apellidos son obligatorios."); return false;
        }
        if (txtUsuario.getText().trim().length() < 5) {
            AlertaUtil.mostrarError("El usuario debe tener al menos 5 caracteres."); return false;
        }
        if (txtContrasena.getText().trim().length() < 8) {
            AlertaUtil.mostrarError("La contraseña debe tener al menos 8 caracteres."); return false;
        }
        if (dpFecha.getValue() == null) {
            AlertaUtil.mostrarError("Debe seleccionar una fecha de nacimiento."); return false;
        }
        if (cbDepartamento.getValue() == null || lvCursos.getSelectionModel().getSelectedItem() == null) {
            AlertaUtil.mostrarError("Debe seleccionar departamento y curso."); return false;
        }
        if (!rbPresencial.isSelected() && !rbVirtual.isSelected()) {
            AlertaUtil.mostrarError("Debe elegir una modalidad."); return false;
        }
        if (!chkManana.isSelected() && !chkTarde.isSelected()) {
            AlertaUtil.mostrarError("Debe elegir un horario."); return false;
        }
        if (!chkNormas.isSelected()) {
            AlertaUtil.mostrarError("Debe aceptar las normas."); return false;
        }
        return true;
    }
}