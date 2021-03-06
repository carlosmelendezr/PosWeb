package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelos.factura.Banco;

import java.net.URL;
import java.util.ResourceBundle;

public class Tipopago implements Initializable {

    @FXML
    Button btnVisa;

    @FXML
    Button btnMaster;

    @FXML
    Button btnMaestro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Image imgvisa = new Image("images/visa.png");
        ImageView viewvisa = new ImageView(imgvisa);
        viewvisa.setFitHeight(50);
        viewvisa.setPreserveRatio(true);

        Image imgmaster = new Image("images/master.png");
        ImageView viewmaster = new ImageView(imgmaster);
        viewmaster.setFitHeight(50);
        viewmaster.setPreserveRatio(true);

        Image imgmaestro = new Image("images/maestro.png");
        ImageView viewmaestro = new ImageView(imgmaestro);
        viewmaestro.setFitHeight(50);
        viewmaestro.setPreserveRatio(true);

        btnVisa.setGraphic(viewvisa);
        btnVisa.setPrefSize(60, 60);

        btnMaster.setGraphic(viewmaster);
        btnMaster.setPrefSize(60, 60);

        btnMaestro.setGraphic(viewmaestro);
        btnMaestro.setPrefSize(60, 60);

    }

    public void botonVisa(MouseEvent event) {
        Contexto.bancoSeleccionado = 1;
        ((Node)(event.getSource())).getScene().getWindow().hide();

    }

    public void botonMaster(MouseEvent event) {
        Contexto.bancoSeleccionado = 2;
        ((Node)(event.getSource())).getScene().getWindow().hide();

    }

    public void botonMaestro(MouseEvent event) {
        Contexto.bancoSeleccionado = 3;
        ((Node)(event.getSource())).getScene().getWindow().hide();

    }
}
