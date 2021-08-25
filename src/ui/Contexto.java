package ui;

import modelos.factura.Cliente;
import modelos.factura.Producto;

import java.util.ArrayList;
import java.util.List;

public class Contexto {


    public static List<String> resultadoBusqueda;
    public static Integer seleccionBusqueda;
    public static Cliente Cli;
    public static Producto ProductoBuscado;

    public static void inicializar() {
        resultadoBusqueda = new ArrayList<>();
        seleccionBusqueda = 0;
        Cli = new Cliente();
    }



    public static String getCodigoSeleccionado() {
        return resultadoBusqueda.get(seleccionBusqueda);
    }

    public static List<String> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public static void setResultadoBusqueda(List<String> resultadoBusqueda) {
        Contexto.resultadoBusqueda = resultadoBusqueda;
    }

    public static Integer getSeleccionBusqueda() {
        return seleccionBusqueda;
    }

    public static void setSeleccionBusqueda(Integer seleccionBusqueda) {
        Contexto.seleccionBusqueda = seleccionBusqueda;
    }
}
