package ui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelos.datos.Operaciones;
import modelos.factura.Cliente;
import modelos.factura.MonedaUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ClienteCtl implements Initializable {

    @FXML
    TextField cedula;
    @FXML
    Label rif;
    @FXML
    Label direccion;
    @FXML
    Label razonsoc;
    @FXML
    Label tasadolar;
    @FXML
    Label factura;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cedula.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                buscar();
            }
        } );
        inicializa();
    }

    void inicializa() {
        tasadolar.setText( MonedaUtil.formatoBs.format(Contexto.Bolivar.getTasacambio().getValor()));
        factura.setText(Contexto.facturaActual.getNumeroFactura().toString());
    }



    public void buscar() {

        if (cedula.getText() != null) {
            if (!cedula.getText().isEmpty()) {
                Cliente cli = Operaciones.buscarClienteCedula(cedula.getText());
                if (! (cli==null)) {
                    Contexto.facturaActual.setCliente(cli); ;
                    asignaCliente();
                }
            }

        }

    }

    private void asignaCliente() {
        Cliente Cli = Contexto.facturaActual.getCliente();
        if (!(Cli==null)) {

            cedula.setText(Cli.getRif().toString());
            razonsoc.setText(Cli.getRazonsocial());
            direccion.setText(Cli.lineaDireccion());
            rif.setText(Cli.getTiporif() + "-" + Cli.getRif());

        }

    }

    public void ventanaCrear() {
        if (Acciones.ventanaCrearCliente()) {
            asignaCliente();
        }
    }



}
