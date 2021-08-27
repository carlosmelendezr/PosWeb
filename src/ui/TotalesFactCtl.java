package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ResourceBundle;

public class TotalesFactCtl implements Initializable {

    @FXML
    Label Total;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Total.setText("0");
        Total.textProperty().bind(Contexto.totalfactura);

    }



}
