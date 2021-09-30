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
import modelos.datos.dboBanco;
import modelos.factura.Banco;
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
    TextField transferenciaBs;
    @FXML
    TextField PagoMovil;

    @FXML
    TextField tarjetaBs;
    @FXML
    TextField tarjetaInt;
    @FXML
    TextField zelle;
    @FXML
    TextField referencia;
    @FXML
    TextField descuento;
    @FXML
    Button botonImprimir;
    @FXML
    Button botonGuardar;


    @FXML
    TableView listaPagos;

    private int MouseClicks;
    private Banco ban;

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
                if (!(tarjetaInt.getText().isEmpty()
                        && tarjetaBs.getText().isEmpty()
                        && zelle.getText().isEmpty())) {
                    entradaTarjeta();
                } else {
                    if (!transferenciaBs.getText().isEmpty()) {
                        entradaTransferencia();
                    } else {
                        entradaPagoMovil();
                    }
                }

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

        descuento.textProperty().addListener(new soloNumero(descuento) );
        descuento.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                if (descuento.getText().isEmpty()) {
                    llenarDesc();
                    return;
                }
                entradaDescuento();
            } else {
                //Utilidades.entradaFormatoBs(efectivoBs);

            }
        } );

        transferenciaBs.textProperty().addListener(new soloNumero(transferenciaBs) );
        transferenciaBs.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                referencia.requestFocus();

            }
        } );

        PagoMovil.textProperty().addListener(new soloNumero(PagoMovil) );
        PagoMovil.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                referencia.requestFocus();


            }
        } );

        TableColumn coltipo = new TableColumn("Moneda");
        coltipo.setMinWidth(100);
        coltipo.setCellValueFactory(
                new PropertyValueFactory<Pago, String>("destipoMoneda"));

        TableColumn desbanco = new TableColumn("Pago");
        desbanco.setMinWidth(100);
        desbanco.setCellValueFactory(
                new PropertyValueFactory<Pago, String>("descBanco"));

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


        listaPagos.getColumns().addAll(coltipo,desbanco,colmonto,colref,colmontodolar);
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
        descuento.setText("");
        transferenciaBs.setText("");
        PagoMovil.setText("");

        Contexto.actualizaPagos();

        totalPagosBolivares.setText(Contexto.totalPagoBs());
        totalPagosDolares.setText(Contexto.totalPagoDolares());
        totalPagos.setText(Contexto.totalPago());
        saldo.setText(Contexto.totalSaldo());
        saldobs.setText(Contexto.totalSaldoBs());

        if (Contexto.facturaActual.getPagada()) {
            botonImprimir.setVisible(true);
            botonGuardar.setVisible(true);
        }

        MouseClicks = 0;
        efectivoBs.requestFocus();

        Scene sc = efectivoBs.getScene();


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
            ban = dboBanco.obtenerBanco(11);
            Pago pagoEfecBs = new Pago(Contexto.Bolivar, efecBs, vueltoBs);
            pagoEfecBs.setReferencia("EFECTIVO");
            pagoEfecBs.setBanco(ban);

            Contexto.enviarEstus(Contexto.facturaActual.agregarPago(pagoEfecBs));
            inicializa();
        }
    }

    void entradaEfectivoDivisa() {

        if (efectivoDolar.getText().isEmpty()) return;

        Moneda efecDolar = Utilidades.textoToMoneda(efectivoDolar.getText());
        Moneda vueltoBs = new Moneda(0);

        if (!efecDolar.igualZero()) {
            ban = dboBanco.obtenerBanco(12);
            Pago pagoEfecDolar = new Pago(Contexto.Dolar, efecDolar, vueltoBs);
            pagoEfecDolar.setReferencia("EFECTIVO $");
            pagoEfecDolar.setBanco(ban);

            Contexto.enviarEstus(Contexto.facturaActual.agregarPago(pagoEfecDolar));
            inicializa();
        }
    }

    void entradaDescuento() {

        if (descuento.getText().isEmpty()) return;

        Moneda efecDolar = Utilidades.textoToMoneda(descuento.getText());
        Moneda vueltoBs = new Moneda(0);

        if (!efecDolar.igualZero()) {
            ban = dboBanco.obtenerBanco(10);

            Pago pagoEfecDolar = new Pago(Contexto.Dolar, efecDolar, vueltoBs);
            pagoEfecDolar.setReferencia("DESCUENTO");
            pagoEfecDolar.setBanco(ban);

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
            Acciones.tipoPago();
            switch (Contexto.bancoSeleccionado) {
                case 1:
                    {Contexto.bancoSeleccionado = 7;break;}
                case 2:
                    {Contexto.bancoSeleccionado = 8;break;}

            }

        }

        if (!tarjetaBs.getText().isEmpty()) {
            valor = tarjetaBs.getText();
            mon = Contexto.Bolivar;
            Acciones.tipoPago();
        }

        if (!zelle.getText().isEmpty()) {
            valor = zelle.getText();
            mon = Contexto.Dolar;
            Contexto.bancoSeleccionado = 6;
        }

        Moneda tarjetaValor = Utilidades.textoToMoneda(valor);
        Moneda vueltoBs = new Moneda(0);

        ban = dboBanco.obtenerBanco(Contexto.bancoSeleccionado);

        if (!tarjetaValor.igualZero()) {

            Pago pagoTarDolar = new Pago(mon, tarjetaValor, vueltoBs);
            pagoTarDolar.setReferencia(referencia.getText());
            pagoTarDolar.setBanco(ban);

            Contexto.enviarEstus(Contexto.facturaActual.agregarPago(pagoTarDolar));
            inicializa();
        }
    }

    void entradaTransferencia() {
        String valor="";
        TipoMoneda mon = null;

        if (transferenciaBs.getText().isEmpty())
                 return;

        if (!transferenciaBs.getText().isEmpty()) {
            valor = transferenciaBs.getText();
            mon = Contexto.Bolivar;
        }

        Moneda Valor = Utilidades.textoToMoneda(valor);
        Moneda vueltoBs = new Moneda(0);

        if (!Valor.igualZero()) {
            ban = dboBanco.obtenerBanco(4);
            Pago pagoTarDolar = new Pago(mon, Valor, vueltoBs);
            pagoTarDolar.setReferencia(referencia.getText());
            pagoTarDolar.setBanco(ban);

            Contexto.enviarEstus(Contexto.facturaActual.agregarPago(pagoTarDolar));
            inicializa();
        }
    }

    void entradaPagoMovil() {
        String valor="";
        TipoMoneda mon = null;

        if (PagoMovil.getText().isEmpty())
            return;

        if (!PagoMovil.getText().isEmpty()) {
            valor = PagoMovil.getText();
            mon = Contexto.Bolivar;
        }

        Moneda Valor = Utilidades.textoToMoneda(valor);
        Moneda vueltoBs = new Moneda(0);

        if (!Valor.igualZero()) {
            ban = dboBanco.obtenerBanco(5);
            Pago pagoTarDolar = new Pago(mon, Valor, vueltoBs);
            pagoTarDolar.setReferencia(referencia.getText());
            pagoTarDolar.setBanco(ban);

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

    public void llenarDesc() {
        MouseClicks ++;
        if (MouseClicks>1) {
            String txt = Contexto.totalSaldo().replace(".", "");
            descuento.setText(txt.replace(",", "."));
            MouseClicks = 0;
        }
    }

    public void imprimirFactura(MouseEvent event) {

        Contexto.finalizarFactura();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void guardarFactura(MouseEvent event) {

        Contexto.guardarFactura();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }



}
