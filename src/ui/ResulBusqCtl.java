package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class ResulBusqCtl implements Initializable {

    @FXML
    ListView listaBusqueda;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listaBusqueda.getItems().addAll(Contexto.resultadoBusqueda);


    }

    public void MouseClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            int id = listaBusqueda.getSelectionModel().getSelectedIndex();
            System.out.println(Contexto.resultadoBusqueda.get(id));
            Contexto.setSeleccionBusqueda(id);

            ((Node)(event.getSource())).getScene().getWindow().hide();


        }
    }


}
