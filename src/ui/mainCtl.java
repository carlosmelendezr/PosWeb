package ui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainCtl implements Initializable {

    @FXML
    BorderPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
          crearPantalla();
    }

    public void crearPantalla() {
        VBox vboxsup = new VBox();
        VBox vboxinf = new VBox();
        try {

            FXMLLoader cliLoader = new FXMLLoader(getClass().getResource("cliente.fxml"));
            vboxsup.getChildren().add(cliLoader.load());

            FXMLLoader busqLoader = new FXMLLoader(getClass().getResource("buscador.fxml"));
            vboxsup.getChildren().add(busqLoader.load());
            mainPane.setTop(vboxsup);

            FXMLLoader artLoader = new FXMLLoader(getClass().getResource("articulos.fxml"));
            mainPane.setCenter(artLoader.load());

            FXMLLoader totalLoader = new FXMLLoader(getClass().getResource("totalesfact.fxml"));
            FXMLLoader estatLoader = new FXMLLoader(getClass().getResource("estatus.fxml"));
            vboxinf.getChildren().add(totalLoader.load());
            vboxinf.getChildren().add(estatLoader.load());
            mainPane.setBottom(vboxinf);

            cintaBotones();

        } catch (IOException e) {
            System.out.println("Error :"+e.getMessage());
            e.printStackTrace();
        }
    }

    public void cintaBotones() {

        VBox vboxder = new VBox();

        Button buttonPagos = new Button("Pagos");
        botonset(buttonPagos);
        buttonPagos.setOnAction(event -> {
            Acciones.ventanaPagos();
        });

        /*Button buttonFinalizar = new Button("Imprimir");
        botonset(buttonFinalizar);
        buttonFinalizar.setOnAction(event -> {
            Contexto.finalizarFactura();
        });*/
        vboxder.getChildren().add(buttonPagos);
        //vboxder.getChildren().add(buttonFinalizar);
        mainPane.setRight(vboxder);
    }

    public void botonset(Button bot) {
        bot.setPrefWidth(100);
        bot.setPrefHeight(100);
    }


}