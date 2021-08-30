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
                      Acciones.ventanaBuscarProducto();
                    }
                    if (!(Contexto.getCodigoSeleccionado()==null)) {
                        TextoBuscar.setText(Contexto.getCodigoSeleccionado());
                    }
                }
            }
       }
    }

    public void buscarProducto(String codigoref) {

        Producto pro = Operaciones.buscarProductoCodigo(codigoref);

        if (pro!=null ) {
            Contexto.ProductoBuscado = pro;
            LineaFactura lin = new LineaFactura(0,
                    Contexto.ProductoBuscado,1.0);
            Contexto.agregarLineaFactura(lin);
            TextoBuscar.setText("");

        } else {
            Contexto.enviarEstus("El código "+TextoBuscar.getText()+" NO existe.");
            TextoBuscar.setText("");
        }

    }


}
