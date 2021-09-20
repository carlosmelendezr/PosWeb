package ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import modelos.datos.Operaciones;
import modelos.datos.Servicios;
import modelos.factura.LineaFactura;
import modelos.factura.Producto;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class ImportarProductosCtl implements Initializable {


    private int MouseClicks;
    public  ObservableList<Producto> productos;
    public  List<Producto> lista;

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

        TableColumn colcant = new TableColumn("Costo");
        colcant.setMinWidth(100);
        colcant.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("costoFormato"));

        TableColumn colpre = new TableColumn("Precio");
        colpre.setMinWidth(100);
        colpre.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("precioFormato"));

        TableColumn coltipoimp = new TableColumn("Tipo Impuesto");
        coltipoimp.setMinWidth(100);
        coltipoimp.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("idImpuesto"));


        listaArticulos.getColumns().addAll(colcod,colref,coldes,colcant,colpre,coltipoimp);

        listaArticulos.setItems(productos);
        Operaciones.borrarProductosImportar();

    }




    public void procesar() {
        Servicios svr = new Servicios();
        svr.importarProductos(archivo.getText().trim(),"productos_importar");
        productos = observableArrayList();
        lista=Operaciones.consultarImportarProducto();
        System.out.println("Articulos :"+lista.size());
        productos.addAll(lista);
        listaArticulos.setItems(productos);

    }

    public void guardar() {

        boolean exito = false;
        int count =0;
        if (Acciones.dialogoConfirmar("Creación Masiva Artículos","Esta seguro de la operación")) {
            for (Producto pro:lista) {
                exito = Operaciones.InsertarProducto(pro);
                count ++;
            }
        }
        if (exito) {
            Acciones.dialogoAlerta("Resultado del Proceso", "Proceso existoso,"+count+" articulos creados.");
            Operaciones.borrarProductosImportar();
        } else {
            Acciones.dialogoAlerta("Resultado del Proceso", "Error en el proceso,"+count+" articulos creados.");
        }
    }


}

