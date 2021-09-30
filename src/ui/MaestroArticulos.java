package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import modelos.datos.Operaciones;
import modelos.factura.*;
import servicios.impresion.ImprimirEtiqueta;


import java.net.URL;

import java.util.ResourceBundle;

public class MaestroArticulos implements
        Initializable {

    @FXML
    TextField buscar;
    @FXML
    TextField id;
    @FXML
    TextField descrip;
    @FXML
    TextField idtipoimp;
    @FXML
    TextField precio;
    @FXML
    TextField precioiva;
    @FXML
    TextField costo;
    @FXML
    TextField stock;
    @FXML
    TextField ref;
    @FXML
    TextField codbarra;
    @FXML
    Label mensajes;

    private Producto pro;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        buscar.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                buscarProducto();
            }
        } );

    }

    public void guardar() {
        if (pro ==null) {
            mensajes.setText("Debe buscar algun producto a modificar");
            return;
        }

        Moneda proPrecio = null;
        Moneda proCosto = null;
        Double proStock = 0.0;
        try {
            proPrecio = new Moneda(precio.getText().replace(",","."));
            proCosto = new Moneda(costo.getText().replace(",","."));
            proStock = Double.parseDouble(stock.getText().replace(",","."));
        }catch (Exception e) {
            e.printStackTrace();
        }

        if (proPrecio!=null && proCosto!=null) {
            pro.setPrecio(proPrecio);
            pro.setCosto(proCosto);
            pro.setStock(proStock);
            pro.setReferencia(ref.getText().trim());
            pro.setCodigo(codbarra.getText().trim());
            if (Operaciones.ActualizarProducto(pro)) {
                pro = null;
                id.setText("");
                descrip.setText("");
                idtipoimp.setText("");
                costo.setText("");
                precio.setText("");
                stock.setText("");
                ref.setText("");
                codbarra.setText("");
                buscar.setText("");
                mensajes.setText("Producto actualizado correctamente.");
            }
        }

    }




    public void buscarProducto() {

        pro = Operaciones.buscarProductoCodigo(buscar.getText().trim());

        if (pro!=null ) {
            id.setText( pro.getId().toString() );
            descrip.setText(pro.getDescripcion());
            idtipoimp.setText(pro.getIdImpuesto().toString());
            costo.setText(pro.getCostoFormato());
            precio.setText(pro.getPrecioFormato());
            stock.setText(pro.getStock().toString());
            ref.setText(pro.getReferencia());
            codbarra.setText(pro.getCodigo());
            buscar.setText("");

            Moneda precioIVA = new Moneda();

            precioIVA.setValor(pro.getPrecio().getValor());
            precioIVA.sumarIVA(pro.getAlicuota());

            String precioFormato = MonedaUtil.formatoUsd.format( precioIVA.getValor());
            precioiva.setText(precioFormato);

        } else {
            mensajes.setText("El código "+buscar.getText()+" NO existe.");
            buscar.setText("");
        }

    }

    public void etiquetaPrecio() {

        ImprimirEtiqueta etiq = new ImprimirEtiqueta();
        etiq.Precio(pro);


    }

    public void etiquetaProducto() {
        ImprimirEtiqueta etiq = new ImprimirEtiqueta();
        etiq.Barra(pro);

    }

}
