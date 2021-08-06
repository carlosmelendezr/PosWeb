package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuscadorCtl implements Initializable {

    @FXML
    TextField TextoBuscar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void buscar() {

       if ( TextoBuscar.getText() != null ) {

            if (!TextoBuscar.getText().isEmpty()) {

                Contexto.Busqueda();
                if (Contexto.resultadoBusqueda.size() > 0) {
                    ventanaBuscar();
                    TextoBuscar.setText(Contexto.getCodigoSeleccionado());
                }
            }
       }
    }

    public void ventanaBuscar() {

        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(
                    Controller.class.getResource("resulbusq.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Buscador de Productos");
            stage.initModality(Modality.APPLICATION_MODAL);


            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("Error:"+e.getMessage());
        }



    }
}
