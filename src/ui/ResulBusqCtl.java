package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelos.factura.Producto;
import modelos.factura.ProductoBuscar;


import java.net.URL;
import java.util.ResourceBundle;

public class ResulBusqCtl implements Initializable {

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
                new PropertyValueFactory<Producto, String>("ref"));

        TableColumn coldes = new TableColumn("Descripción");
        coldes.setMinWidth(300);
        coldes.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("descripcion"));

        TableColumn colcant = new TableColumn("Existencia");
        colcant.setMinWidth(100);
        colcant.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("cantidad"));

        TableColumn colpre = new TableColumn("Precio");
        colpre.setMinWidth(100);
        colpre.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("precioFormato"));


        listaArticulos.getColumns().addAll(colcod,colref,coldes,colcant,colpre);
        listaArticulos.setItems(Contexto.resultadoBusqueda);


    }

    public void MouseClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            int id = listaArticulos.getSelectionModel().getSelectedIndex();
            ProductoBuscar pro = Contexto.resultadoBusqueda.get(id);
            System.out.println(pro.getCodigo()+" "+pro.getDescripcion());
            Contexto.setSeleccionBusqueda(id);
            Contexto.codigoSeleccionado = pro.getCodigo();
            ((Node)(event.getSource())).getScene().getWindow().hide();


        }
    }


}
