package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import modelos.datos.Servicios;
import modelos.factura.LineaFactura;
import modelos.factura.Producto;

import java.net.URL;
import java.util.ResourceBundle;

public class ImportarProductosCtl implements Initializable {


    private int MouseClicks;

    @FXML
    TableView listaArticulos;

    @FXML
    TextField archivo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MouseClicks = 0;

        TableColumn colcod = new TableColumn("Código");
        colcod.setMinWidth(100);
        colcod.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("codigo"));

        TableColumn colref = new TableColumn("Referencia");
        colref.setMinWidth(100);
        colref.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("referencia"));

        TableColumn coldes = new TableColumn("Descripción");
        coldes.setMinWidth(300);
        coldes.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("descripcion"));

        TableColumn colcant = new TableColumn("Cantidad");
        colcant.setMinWidth(100);
        colcant.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("cantidad"));

        TableColumn colpre = new TableColumn("Precio");
        colpre.setMinWidth(100);
        colpre.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("precioFormato"));

        TableColumn coltot = new TableColumn("Total");
        coltot.setMinWidth(100);
        coltot.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("totalFormato"));

        listaArticulos.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.DELETE ) {
                borrarLinea();
            }
        } );


        listaArticulos.getColumns().addAll(colcod,colref,coldes,colcant,colpre,coltot);

        listaArticulos.setItems(Contexto.facturaListaproductos);

    }

    public static void inicializa() {

    }

    public void colocarCantidad(MouseEvent event) {
        MouseClicks++;
        if (MouseClicks>2) {
            int id = listaArticulos.getSelectionModel().getSelectedIndex();
            LineaFactura lin = Contexto.facturaListaproductos.get(id);
            Integer Cantidad = Acciones.dialogoCantidad(lin.getDescripcion());

            if (Cantidad > 0) {
                lin.setCantidad(lin.getCantidad().doubleValue()+Cantidad.doubleValue());
                Contexto.modificarLineaFactura(id,lin);
            };
            MouseClicks = 0;
        }

    }

    public void borrarLinea() {
        int id = listaArticulos.getSelectionModel().getSelectedIndex();
        LineaFactura lin = Contexto.facturaListaproductos.get(id);
        if (Acciones.dialogoConfirmar("Eliminar "+lin.getDescripcion(),
                "Confirma ?")) {
            Contexto.elimiarLineaFactura(id);
        }
    }

    public void procesar() {
        Servicios svr = new Servicios();
        svr.importarProductos(archivo.getText().trim(),"importar_productos");

    }
}

