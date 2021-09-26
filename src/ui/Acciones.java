package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Acciones {

    public static void ventanaPrincipal() {

        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(
                    Controller.class.getResource("pantallaPrincipal.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("PosWeb");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("Error:"+e.getMessage());
            e.printStackTrace();
        }

    }

    public static void ventanaFacturacion() {

        try {
            Contexto.inicializar();

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(
                    Controller.class.getResource("main.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Facturación");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("Error:"+e.getMessage());
            e.printStackTrace();
        }

    }

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

    public static void ventanaImportarProductos() {

        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(
                    Controller.class.getResource("importar_productos.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Importar Productos");
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

    public static boolean ventanaCrearUsuario() {
        boolean exito = false;
        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(
                    Controller.class.getResource("usuarios.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Creación de Usuarios");
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

    public static void dialogoAlerta(String titulo, String texto) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerta");
        alert.setHeaderText(titulo);
        alert.setContentText(texto);

        alert.showAndWait();

    }

    public static Double dialogoTasa() {
        Double valor = 0.0;

        String actual =Contexto.tasaDolar.getValue();
        actual.replace(",",".");

        TextInputDialog dialog = new TextInputDialog(actual);
        dialog.setTitle("Agregar Tasa Cambio Bs./$");
        dialog.setHeaderText("Nueva tasa de cambio");
        dialog.setContentText("Valor :");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            try {
                valor = Double.parseDouble(result.get());
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return valor;
    }

    public static void dialogoBanco() {

        String [] arrayData = {"First", "Second", "Third", "Fourth"};
        List<String> dialogData;
        ChoiceDialog dialog;


        dialogData = Arrays.asList(arrayData);

        dialog = new ChoiceDialog(dialogData.get(0), dialogData);
        dialog.setTitle("Selección del Pago");
        dialog.setHeaderText("Tipo de Pago");

        Optional<String> result = dialog.showAndWait();
        String selected = "cancelled.";

        if (result.isPresent()) {

            selected = result.get();
        }


    }

    public static boolean tipoPago() {
        boolean exito = false;
        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(
                    Controller.class.getResource("tipopago.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Seleccion de Tipo de Pago");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            exito = true;

        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
        return exito;
    }
}
