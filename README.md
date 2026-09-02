# SistemaMatricula# Caso 1: Sistema de matrícula



## Descripción
El centro necesita una aplicación para registrar estudiantes en sus cursos de programación, Excel, redes y diseño gráfico.

## Integrantes
* Nora Obregón
* Steven Flores

---

## Datos y controles requeridos

| Información | Control JavaFX |
| :--- | :--- |
| Nombres, apellidos y usuario | `TextField` |
| Contraseña | `PasswordField` |
| Fecha de nacimiento | `DatePicker` |
| Departamento | `ComboBox` |
| Curso | `ListView` |
| Modalidad presencial o virtual | `RadioButton` |
| Horarios y aceptación de normas | `CheckBox` |
| Logotipo del centro | `ImageView` |

---

## Especificaciones del Sistema

* **TableView:** Muestre nombre completo, departamento, curso, modalidad, horario y fecha de nacimiento.
* **Validaciones:** 
  * Ningún campo puede quedar vacío.
  * El usuario debe tener al menos 5 caracteres.
  * La contraseña debe contener al menos 8 caracteres.
  * Se debe elegir curso, modalidad, horario y aceptar las normas.
  * Mostrar los errores mediante `Alert`.
