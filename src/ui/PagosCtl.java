package ui;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import modelos.factura.Moneda;
import modelos.factura.Pago;
import modelos.factura.Producto;

import java.net.URL;
import java.util.ResourceBundle;

public class PagosCtl implements Initializable {

    @FXML
    Label totalFactura;
    @FXML
    Label totalPagosBolivares;
    @FXML
    Label totalPagosDolares;
    @FXML
    TextField efectivoBs;
    @FXML
    TextField efectivoDolar;
    @FXML
    TextField efectivoEuro;

    @FXML
    TableView listaPagos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       totalFactura.setText(TotalFactCtl.totalGen.getValue());


       efectivoBs.textProperty().addListener(new soloNumero(efectivoBs) );
       efectivoBs.setOnKeyPressed( event -> {
           if( event.getCode() == KeyCode.ENTER ) {
               entradaEfectivoBs();
           } else {
               //Utilidades.entradaFormatoBs(efectivoBs);

           }
       } );

        TableColumn coltipo = new TableColumn("Moneda");
        coltipo.setMinWidth(100);
        coltipo.setCellValueFactory(
                new PropertyValueFactory<Pago, String>("destipoMoneda"));

        TableColumn colmonto = new TableColumn("Monto");
        colmonto.setMinWidth(100);
        colmonto.setCellValueFactory(
                new PropertyValueFactory<Pago, String>("montoformato"));

        TableColumn colref = new TableColumn("Referencia");
        colref.setMinWidth(100);
        colref.setCellValueFactory(
                new PropertyValueFactory<Pago, String>("referencia"));

        listaPagos.getColumns().addAll(coltipo,colmonto,colref);
        listaPagos.setItems(Contexto.facturaListapagos);
    }

    void inicializa() {
        efectivoBs.setText("");
        efectivoDolar.setText("");
        efectivoEuro.setText("");

        Contexto.actualizaPagos();

        totalPagosBolivares.setText(Contexto.totalPagoBs());
    }

    void entradaEfectivoBs() {

        Moneda efecBs = Utilidades.textoToMoneda(efectivoBs.getText());
        Moneda vueltoBs = new Moneda(0);

        if (!efecBs.igualZero()) {

            Pago pagoEfecBs = new Pago(Contexto.Bolivar, efecBs, vueltoBs);
            pagoEfecBs.setReferencia("EFECTIVO");


            Contexto.enviarEstus(Contexto.facturaActual.agregarPago(pagoEfecBs));
            inicializa();
        }
    }

}
