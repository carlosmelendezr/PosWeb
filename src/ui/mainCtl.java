package ui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

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
        try {

            FXMLLoader cliLoader = new FXMLLoader(getClass().getResource("cliente.fxml"));
            vboxsup.getChildren().add(cliLoader.load());

            FXMLLoader busqLoader = new FXMLLoader(getClass().getResource("buscador.fxml"));
            vboxsup.getChildren().add(busqLoader.load());
            mainPane.setTop(vboxsup);

            FXMLLoader artLoader = new FXMLLoader(getClass().getResource("articulos.fxml"));
            mainPane.setCenter(artLoader.load());

            FXMLLoader totalLoader = new FXMLLoader(getClass().getResource("totalesfact.fxml"));
            mainPane.setBottom(totalLoader.load());




        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}