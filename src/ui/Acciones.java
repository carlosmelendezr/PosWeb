package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Acciones {

    public static void ventanaPagos() {

        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(
                    Controller.class.getResource("pagos.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Procesar Pagos");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("Error:"+e.getMessage());
            e.printStackTrace();
        }

    }

    public static void ventanaBuscarProducto() {

        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(
                    Controller.class.getResource("resulbusq.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Buscador de Productos");
            stage.initModality(Modality.APPLICATION_MODAL);


            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("Error:"+e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean ventanaCrearCliente() {
        boolean exito = false;
        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(
                    Controller.class.getResource("crearcliente.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Crear o Modificar Cliente");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            exito = true;

        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
        return exito;
    }

    public static Integer dialogoCantidad(String texto) {
        Integer Cantidad = 0;

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Sumar Cantidad de Artículos");
        dialog.setHeaderText(texto);
        dialog.setContentText("Cantidad :");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            try {
              Cantidad = Integer.parseInt(result.get());
            }catch (Exception e) {
                Cantidad = 0;
            }
            System.out.println("Cantidad : " + Cantidad);
        }
     return Cantidad;
    }

    public static boolean dialogoConfirmar(String titulo, String texto) {
        boolean respuesta = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(titulo);
        alert.setContentText(texto);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            respuesta = true;
        }
        return respuesta;
    }
}
