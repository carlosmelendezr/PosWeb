package ui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelos.factura.Producto;


import java.net.URL;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class ArticulosCtl implements Initializable {

    @FXML
    TableView listaArticulos;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

        TableColumn colpre = new TableColumn("Precio");
        colpre.setMinWidth(100);
        colpre.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("precioFormato"));


        listaArticulos.getColumns().addAll(colcod,colref,coldes,colpre);
        listaArticulos.setItems(Contexto.facturaListaproductos);

    }
}
