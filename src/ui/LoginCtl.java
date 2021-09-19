package ui;



import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Window;
import modelos.datos.Usuario;
import modelos.datos.dboUsuarios;

public class LoginCtl implements Initializable {

    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    public void login(ActionEvent event)  {

        Window owner = submitButton.getScene().getWindow();



        if (emailIdField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error de acceso!",
                    "Coloque su código de usuario");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error de acceso!",
                    "Coloque su contraseña");
            return;
        }

        boolean flag = false;

        Integer codigo = Integer.parseInt(emailIdField.getText());
        String password = passwordField.getText();

        Usuario usr = dboUsuarios.buscarUsuarioCedula(codigo,password);
        if (usr!=null) flag = true;


        if (!flag) {
            infoBox("Usuario o contraseña incorrecta", null, "Inicio de sesión");
        } else {
            //infoBox("Acceso autorizado!", null, "Autorizado");
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Acciones.ventanaPrincipal();
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        emailIdField.textProperty().addListener(new soloNumero(emailIdField) );

    }
}