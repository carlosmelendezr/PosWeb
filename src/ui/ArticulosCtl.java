package ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;

public class ArticulosCtl implements Initializable {

    @FXML
    ListView listaArticulos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       listaArticulos.getItems().add("ARTICULO 1");
       listaArticulos.getItems().add("ARTICULO 2");
    }
}
