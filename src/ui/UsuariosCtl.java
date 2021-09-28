package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import modelos.datos.CodigosArea;
import modelos.datos.Operaciones;
import modelos.datos.Usuario;
import modelos.datos.dboUsuarios;
import modelos.factura.Cliente;
import modelos.factura.Direccion;
import modelos.factura.Telefono;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class UsuariosCtl implements Initializable {

    @FXML
    TextField cedula;
    @FXML
    TextField razonsoc;
    @FXML
    TextField clave;
    @FXML
    TextField clave2;

    @FXML
    Button guardar;
    @FXML
    Label tipodoc;

    @FXML
    Label mensajes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        limpiar();

        cedula.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {

            }
        });
        razonsoc.setOnMouseEntered(event -> {
            razonsoc.setStyle("-fx-background-color: lightgreen");
        });
        cedula.setOnMouseEntered(event -> {
            cedula.setStyle("-fx-background-color: lightgreen");
        });
        cedula.textProperty().addListener(new soloNumero(cedula));
    }

    private void limpiar() {
        cedula.setText("");
        razonsoc.setText("");
        clave.setText("");
        clave2.setText("");
    }

    public void guardarusuario() {
        if (cedula.getText().isEmpty()) {
            cedula.setStyle("-fx-background-color: red");
            mensajes.setText("La cédula o RIF no puede estar en blanco.");
            return;
        }
        if (razonsoc.getText().isEmpty()) {
            razonsoc.setStyle("-fx-background-color: red");
            mensajes.setText("El nombre no puede estar en blanco.");
            return;
        }

        if (!clave.getText().equals(clave2.getText())) {
            mensajes.setText("Las contraseñas NO coinciden !!!");
            return;
        }

        Usuario usr = new Usuario();
        usr.setCedula(Integer.parseInt(cedula.getText()));
        usr.setEstatus(0);
        usr.setIdrol(0);
        usr.setNombre(razonsoc.getText().toUpperCase(Locale.ROOT));
        usr.setClave(clave.getText().trim());

        if (dboUsuarios.InsertarUsuario(usr)) {
            guardar.setStyle("-fx-background-color: green");
            mensajes.setText("Datos guardados con éxito.");
            limpiar();
        } else {
            mensajes.setText("Error al guardar la informacion.");
        }

    }


}
