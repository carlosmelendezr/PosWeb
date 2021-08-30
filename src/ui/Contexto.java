package ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import modelos.factura.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import static javafx.collections.FXCollections.observableArrayList;

public class Contexto {

    public static List<String> resultadoBusqueda;
    public static Integer seleccionBusqueda;
    public static Cliente Cli;
    public static Producto ProductoBuscado;
    public static ObservableList<LineaFactura> facturaListaproductos;
    public static ObservableList<Pago> facturaListapagos;
    public static Moneda tasaDolarHoy;
    public static Factura facturaActual;
    public static TipoMoneda Dolar;
    public static TipoMoneda Bolivar;
    public static ArrayList<Moneda> tasasImpuesto;
    public static SimpleStringProperty MensajeEstatus = new SimpleStringProperty();
    public static FacturaTotal totalUsd;


    public static void inicializar() {

        tasasImpuesto = new ArrayList<Moneda>(Arrays.asList(new Moneda(0),new Moneda(16.0)));

        resultadoBusqueda = new ArrayList<>();
        seleccionBusqueda = 0;
        MensajeEstatus.set("Preparado");

        facturaListaproductos = observableArrayList();
        facturaListapagos = observableArrayList();

        tasaDolarHoy = new Moneda("4147300.52");
        MonedaUtil.inicializar();

        Dolar = new TipoMoneda(1,"USD","DOLAR","$",new Moneda("1"),MonedaUtil.formatoUsd);
        Dolar.setEsMonedaBase(true);
        Bolivar = new TipoMoneda(2,"VES","BOLIVAR","Bs.",tasaDolarHoy, MonedaUtil.formatoBs);

        facturaActual = new Factura(Dolar);

    }

    public static void enviarEstus(String Mensaje) {
        MensajeEstatus.set(Mensaje);
    }

    public static void agregarLineaFactura(LineaFactura lin) {

        facturaActual.agregarLinea(lin);
        facturaListaproductos.add(lin);
        totalUsd = facturaActual.getTotales();
        ProductoBuscado = null;
        TotalFactCtl.totalGen.set(totalUsd.montoTotalFormato());
        enviarEstus("Producto agregado correctamente.");

    }
    public static void actualizaPagos() {
        facturaListapagos.clear();
        List<Pago> lista = facturaActual.getPagos();
        for(Pago pag:lista) {
            facturaListapagos.add(pag);
        }
    }

    public static String totalPagoBs() {
        Moneda total = facturaActual.getTotalPagos(Bolivar);
        return MonedaUtil.formatoBs.format(total.getValor());
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
