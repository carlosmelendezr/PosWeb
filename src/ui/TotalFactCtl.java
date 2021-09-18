package ui;


import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


import java.net.URL;
import java.util.ResourceBundle;

public class TotalFactCtl implements Initializable {




    @FXML
    Label totalGeneral;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //totalGen.set("0.0");
        totalGeneral.textProperty().bind(Contexto.totalGen);
        Contexto.actulizaTotales();

    }
}
