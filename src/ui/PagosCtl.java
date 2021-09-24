package ui;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.MouseEvent;
import modelos.factura.Moneda;
import modelos.factura.Pago;
import modelos.factura.TipoMoneda;


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
    Label totalPagos;
    @FXML
    Label saldo;
    @FXML
    Label saldobs;
    @FXML
    TextField efectivoBs;
    @FXML
    TextField efectivoDolar;
    @FXML
    TextField efectivoEuro;

    @FXML
    TextField tarjetaBs;
    @FXML
    TextField tarjetaInt;
    @FXML
    TextField zelle;
    @FXML
    TextField referencia;
    @FXML
    Button botonImprimir;


    @FXML
    TableView listaPagos;

    private int MouseClicks;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       totalFactura.setText(Contexto.totalGen.getValue());


       efectivoBs.textProperty().addListener(new soloNumero(efectivoBs) );
       efectivoBs.setOnKeyPressed( event -> {
           if( event.getCode() == KeyCode.ENTER ) {
               if (efectivoBs.getText().isEmpty()) {
                   llenarBs();
                   return;
               }
               entradaEfectivoBs();
           } else {
               //Utilidades.entradaFormatoBs(efectivoBs);

           }
       } );

       tarjetaInt.textProperty().addListener(new soloNumero(tarjetaInt) );
        tarjetaInt.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
               referencia.requestFocus();

            } else {
               tarjetaBs.setText("");
               zelle.setText("");

            }
        } );

        tarjetaBs.textProperty().addListener(new soloNumero(tarjetaBs) );
        tarjetaBs.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                referencia.requestFocus();

            } else {
                tarjetaInt.setText("");
                zelle.setText("");

            }
        } );

        zelle.textProperty().addListener(new soloNumero(zelle) );
        zelle.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                referencia.requestFocus();

            } else {
                tarjetaInt.setText("");
                tarjetaBs.setText("");

            }
        } );

        referencia.textProperty().addListener(new soloNumero(referencia) );
        referencia.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                entradaTarjeta();

            } else {
                //Utilidades.entradaFormatoBs(efectivoBs);

            }
        } );


        efectivoDolar.textProperty().addListener(new soloNumero(efectivoDolar) );
        efectivoDolar.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                if (efectivoDolar.getText().isEmpty()) {
                    llenarDolar();
                    return;
                }
                entradaEfectivoDivisa();
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

        TableColumn colmontodolar = new TableColumn("Dolar");
        colmontodolar.setMinWidth(100);
        colmontodolar.setCellValueFactory(
                new PropertyValueFactory<Pago, String>("montoformatobase"));


        listaPagos.getColumns().addAll(coltipo,colmonto,colref,colmontodolar);
        listaPagos.setItems(Contexto.facturaListapagos);


        inicializa();
    }

    void inicializa() {
        efectivoBs.setText("");
        efectivoDolar.setText("");
        efectivoEuro.setText("");
        tarjetaBs.setText("");
        tarjetaInt.setText("");
        zelle.setText("");
        referencia.setText("");

        Contexto.actualizaPagos();

        totalPagosBolivares.setText(Contexto.totalPagoBs());
        totalPagosDolares.setText(Contexto.totalPagoDolares());
        totalPagos.setText(Contexto.totalPago());
        saldo.setText(Contexto.totalSaldo());
        saldobs.setText(Contexto.totalSaldoBs());

        if (Contexto.facturaActual.getImprimible()) {
            botonImprimir.setVisible(true);
        }

        MouseClicks = 0;
        efectivoBs.requestFocus();

        Scene sc = efectivoBs.getScene();

//        sc.getAccelerators().put(new KeyCodeCombination(KeyCode.F5), botonImprimir::fire);

    }

    public void limpiarPagos() {
        Contexto.facturaActual.limpiarPagos();
        inicializa();
    }

    void entradaEfectivoBs() {

        if (efectivoBs.getText().isEmpty()) return;

        Moneda efecBs = Utilidades.textoToMoneda(efectivoBs.getText());
        Moneda vueltoBs = new Moneda(0);

        if (!efecBs.igualZero()) {

            Pago pagoEfecBs = new Pago(Contexto.Bolivar, efecBs, vueltoBs);
            pagoEfecBs.setReferencia("EFECTIVO");


            Contexto.enviarEstus(Contexto.facturaActual.agregarPago(pagoEfecBs));
            inicializa();
        }
    }

    void entradaEfectivoDivisa() {

        if (efectivoDolar.getText().isEmpty()) return;

        Moneda efecDolar = Utilidades.textoToMoneda(efectivoDolar.getText());
        Moneda vueltoBs = new Moneda(0);

        if (!efecDolar.igualZero()) {

            Pago pagoEfecDolar = new Pago(Contexto.Dolar, efecDolar, vueltoBs);
            pagoEfecDolar.setReferencia("EFECTIVO $");


            Contexto.enviarEstus(Contexto.facturaActual.agregarPago(pagoEfecDolar));
            inicializa();
        }
    }

    void entradaTarjeta() {
        String valor="";
        TipoMoneda mon = null;

        if (tarjetaInt.getText().isEmpty()
                && tarjetaBs.getText().isEmpty()
                && zelle.getText().isEmpty()) return;

        if (!tarjetaInt.getText().isEmpty()) {
            valor = tarjetaInt.getText();
            mon = Contexto.Dolar;
            Acciones.dialogoBanco();
        }

        if (!tarjetaBs.getText().isEmpty()) {
            valor = tarjetaBs.getText();
            mon = Contexto.Bolivar;
            Acciones.dialogoBanco();
        }

        if (!zelle.getText().isEmpty()) {
            valor = zelle.getText();
            mon = Contexto.Dolar;
        }

        Moneda tarjetaValor = Utilidades.textoToMoneda(valor);
        Moneda vueltoBs = new Moneda(0);

        if (!tarjetaValor.igualZero()) {

            Pago pagoTarDolar = new Pago(mon, tarjetaValor, vueltoBs);
            pagoTarDolar.setReferencia(referencia.getText());

            Contexto.enviarEstus(Contexto.facturaActual.agregarPago(pagoTarDolar));
            inicializa();
        }
    }

    public void llenarBs() {
        MouseClicks ++;
        if (MouseClicks>1) {
            String txt = Contexto.totalSaldoBs().replace(".", "");
            efectivoBs.setText(txt.replace(",", "."));
            MouseClicks = 0;
        }
    }

    public void llenarDolar() {
        MouseClicks ++;
        if (MouseClicks>1) {
            String txt = Contexto.totalSaldo().replace(".", "");
            efectivoDolar.setText(txt.replace(",", "."));
            MouseClicks = 0;
        }
    }

    public void imprimirFactura(MouseEvent event) {

        //Contexto.facturaActual.GuardarPagos();
        Contexto.finalizarFactura();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }



}
