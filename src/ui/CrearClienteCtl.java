package ui;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import modelos.datos.CodigosArea;
import modelos.datos.Operaciones;
import modelos.factura.Cliente;
import modelos.factura.Direccion;
import modelos.factura.Telefono;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;

public class CrearClienteCtl implements Initializable {

    @FXML
    RadioButton natural;

    @FXML
    RadioButton juridico;

    @FXML
    ComboBox tiporif;

    @FXML
    ComboBox codcelular;
    @FXML
    ComboBox codoficia;
    @FXML
    TextField cedula;
    @FXML
    TextField razonsoc;
    @FXML
    TextField dir1;
    @FXML
    TextField dir2;
    @FXML
    TextField dir3;
    @FXML
    TextField telcelular;
    @FXML
    TextField tellocal;
    @FXML
    TextField teloficina;
    @FXML
    TextField correo;
    @FXML
    Button guardar;
    @FXML
    Label tipodoc;
    @FXML
    Label mensajes;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        limpiar();
        cambiaNatural();
        codigosarea();

        cedula.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {

            }
        } );

        natural.setOnMouseClicked(event -> {
            cambiaNatural();
        });

        juridico.setOnMouseClicked(event -> {
            cambiaJuridico();
        });

        razonsoc.setOnMouseEntered(event -> {
            razonsoc.setStyle("-fx-background-color: lightgreen");
        });
        cedula.setOnMouseEntered(event -> {
            cedula.setStyle("-fx-background-color: lightgreen");
        });
        dir1.setOnMouseEntered(event -> {
            dir1.setStyle("-fx-background-color: lightgreen");
        });
        telcelular.setOnMouseEntered(event -> {
            telcelular.setStyle("-fx-background-color: lightgreen");
        });


        cedula.textProperty().addListener(new soloNumero(cedula) );
        telcelular.textProperty().addListener(new soloNumero(telcelular) );
        tellocal.textProperty().addListener(new soloNumero(tellocal) );
        teloficina.textProperty().addListener(new soloNumero(teloficina) );

    }
    private void limpiar() {
        cedula.setText("");
        razonsoc.setText("");
        dir1.setText("");
        dir2.setText("");
        dir3.setText("");
        telcelular.setText("");
        tellocal.setText("");
        teloficina.setText("");

    }

    public void guardarCliente() {
        if (cedula.getText().isEmpty()) {
            cedula.setStyle("-fx-background-color: red");
            mensajes.setText("La cédula o RIF no puede estar en blanco.");
            return;
        }
        if (razonsoc.getText().isEmpty()) {
            razonsoc.setStyle("-fx-background-color: red");
            mensajes.setText("El nombre no puede estar en blanco.");
            return;
        }
        if (dir1.getText().isEmpty()) {
            dir1.setStyle("-fx-background-color: red");
            mensajes.setText("El cliente debe tener al menos una dirección.");
            return;
        }
        if (telcelular.getText().isEmpty() || codcelular.getValue()==null) {
            telcelular.setStyle("-fx-background-color: red");
            mensajes.setText("El cliente debe tener al menos un teléfono con código de área.");
            return;
        }

        List<Direccion> dirs = new ArrayList<>();
        dirs.add( new Direccion(dir1.getText()));
        dirs.add( new Direccion(dir2.getText()));
        dirs.add( new Direccion(dir3.getText()));

        List<Telefono> tels = new ArrayList<>();

        if (!telcelular.getText().isEmpty()) {
            tels.add(new Telefono(codcelular.getValue().toString(), telcelular.getText()));
        }
        if (!tellocal.getText().isEmpty()) {
            tels.add(new Telefono("0212", tellocal.getText()));
        }
        if (!teloficina.getText().isEmpty()) {
            tels.add(new Telefono(codoficia.getValue().toString(), teloficina.getText()));
        }

        Cliente cli = new Cliente();
        cli.setRazonsocial(razonsoc.getText().toUpperCase(Locale.ROOT));
        cli.setRif(Integer.parseInt(cedula.getText()));
        cli.setTiporif(tiporif.getValue().toString());
        cli.setDirecciones(dirs);
        cli.setTelefonos(tels);
        cli.setCorreo(correo.getText().toLowerCase(Locale.ROOT));

        if (Operaciones.InsertarCliente(cli)) {
            guardar.setStyle("-fx-background-color: green");
            Contexto.Cli = cli;
            mensajes.setText("Datos guardados con éxito.");
            limpiar();
        } else {
            mensajes.setText("Error al guardar la informacion.");
        }

    }

    private void codigosarea() {
        codcelular.getItems().clear();
        CodigosArea cod = new CodigosArea();
        codcelular.getItems().addAll(cod.ListarCelular());
        codoficia.getItems().addAll(cod.ListarLocal());

    }

    private void cambiaNatural() {
        tiporif.getItems().clear();
        tipodoc.setText("Cédula");
        tiporif.getItems().add("V");
        tiporif.getItems().add("E");
        tiporif.setValue("V");
    }
    private void cambiaJuridico() {
        tiporif.getItems().clear();
        tipodoc.setText("RIF");
        tiporif.getItems().add("J");
        tiporif.getItems().add("G");
        tiporif.setValue("J");

    }
}
