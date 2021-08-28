package ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EstatusCtl implements Initializable {

    @FXML
    Label mensajeEstatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mensajeEstatus.setText("Preparado");
        mensajeEstatus.textProperty().bind(Contexto.MensajeEstatus);

    }
}
