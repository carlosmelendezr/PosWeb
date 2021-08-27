package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelos.datos.Operaciones;
import modelos.factura.LineaFactura;
import modelos.factura.Producto;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuscadorCtl implements Initializable {

    @FXML
    TextField TextoBuscar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TextoBuscar.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                buscar();
            }
        } );

    }

    public void buscar() {

       if ( TextoBuscar.getText() != null ) {

            if (!TextoBuscar.getText().isEmpty()) {
                buscarProducto(TextoBuscar.getText());
                if (Contexto.resultadoBusqueda.size() > 0) {

                    if (Contexto.ProductoBuscado.getId()==null) {
                      ventanaBuscar();
                    }
                    if (!(Contexto.getCodigoSeleccionado()==null)) {
                        TextoBuscar.setText(Contexto.getCodigoSeleccionado());
                    }
                }
            }
       }
    }

    public void buscarProducto(String codigoref) {

        //System.out.println(codigoref);
        Contexto.ProductoBuscado = Operaciones.buscarProductoCodigo(codigoref);
        if (Contexto.ProductoBuscado!=null ) {
            LineaFactura lin = new LineaFactura(0,
                    Contexto.ProductoBuscado,1.0);
            Contexto.agregarLineaFactura(lin);
            TextoBuscar.setText("");
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
