package ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import modelos.datos.Constantes;
import modelos.datos.Operaciones;
import modelos.datos.dboTasa;
import modelos.factura.*;
import servicios.impresion.ImpBixolonSRP812;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

public class Contexto {

    public static ObservableList<ProductoBuscar> resultadoBusqueda;
    public static Integer seleccionBusqueda;
    public static String  codigoSeleccionado;
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
    public static SimpleStringProperty totalGen = new SimpleStringProperty();
    public static SimpleStringProperty numeroFactura = new SimpleStringProperty();
    public static SimpleStringProperty tasaDolar = new SimpleStringProperty();
    public static Tasa TasaDia;


    public static void inicializar() {

        tasasImpuesto = new ArrayList<Moneda>(Arrays.asList(new Moneda(0),new Moneda(16.0)));

        resultadoBusqueda = observableArrayList();
        seleccionBusqueda = 0;
        MensajeEstatus.set("Preparado");

        facturaListaproductos = observableArrayList();
        facturaListapagos = observableArrayList();

        TasaDia = dboTasa.buscarUltimaTasa();

        tasaDolarHoy = new Moneda(TasaDia.getValor());
        MonedaUtil.inicializar();

        Dolar = new TipoMoneda(1,"USD","DOLAR","$",new Moneda("1"),MonedaUtil.formatoUsd);
        Dolar.setEsMonedaBase(true);
        Bolivar = new TipoMoneda(2,"VES","BOLIVAR","Bs.",tasaDolarHoy, MonedaUtil.formatoBs);

        facturaActual = Operaciones.UltimaFacturaActiva();
        if (facturaActual==null) {
            facturaActual = Operaciones.CrearFactura(Dolar);
        } else {
            facturaActual.actualizarLineas();
            facturaActual.obtenerPagos();
        }
        facturaListaproductos.addAll(facturaActual.getLineas());
        numeroFactura.set(facturaActual.getNumeroFactura().toString());
        tasaDolar.set(MonedaUtil.formatoBs.format(Contexto.Bolivar.getTasacambio().getValor()));

        actulizaTotales();
    }

    public static void enviarEstus(String Mensaje) {
        MensajeEstatus.set(Mensaje);
    }

    public static void agregarLineaFactura(LineaFactura lin) {

        if (facturaActual.agregarLinea(lin)) {
            facturaListaproductos.add(lin);
            ProductoBuscado = null;
            actulizaTotales();
            enviarEstus("Producto agregado correctamente.");
        } else {
            enviarEstus("Error en base de datos al agregar producto.");
        }

    }

    public static void modificarLineaFactura(int Index,LineaFactura lin) {

        facturaActual.modificarLinea(Index,lin);
        facturaListaproductos.clear();
        facturaListaproductos.addAll(facturaActual.getLineas());
        actulizaTotales();

    }

    public static void elimiarLineaFactura(int Index) {

        facturaActual.eliminarLinea(Index);
        facturaListaproductos.clear();
        facturaListaproductos.addAll(facturaActual.getLineas());
        actulizaTotales();

    }

    public static void actulizaTotales() {
        totalUsd = facturaActual.getTotales();
        totalGen.set(totalUsd.montoTotalFormato());

    }

    public static void actualizaPagos() {
        facturaListapagos.clear();
        List<Pago> lista = facturaActual.getPagos();
        for(Pago pag:lista) {
            facturaListapagos.add(pag);
        }
    }

    public static void finalizarFactura() {
        facturaActual.Finalizar();
        facturaListaproductos.clear();
        facturaListaproductos.addAll(facturaActual.getLineas());

        facturaActual = Operaciones.CrearFactura(Dolar);
        numeroFactura.set(facturaActual.getNumeroFactura().toString());

        actulizaTotales();
        enviarEstus("Factura guardada correctamente.");

    }

    public static void emitirReporteZ() {
        ImpBixolonSRP812 Bixolon = new ImpBixolonSRP812();

        Bixolon.inicializar("COM99", Dolar);
        Bixolon.cargarTablaComandos();
        Bixolon.ReporteZ();
        Bixolon.finalizar();
    }

    public static String totalPagoBs() {
        Moneda total = facturaActual.getTotalPagosPorTipo(Bolivar);
        return MonedaUtil.formatoBs.format(total.getValor());
    }

    public static String totalPagoDolares() {
        Moneda total = facturaActual.getTotalPagosPorTipo(Dolar);
        return MonedaUtil.formatoBs.format(total.getValor());
    }
    public static String totalPago() {
        Moneda total = facturaActual.getTotalPagos();
        return MonedaUtil.formatoBs.format(total.getValor());
    }
    public static String totalSaldo() {
       return MonedaUtil.formatoBs.format(facturaActual.getFacturaSaldo().getValor());
    }
    public static String totalSaldoBs() {
        Moneda saldobs = MonedaUtil.ConvertirValor(Dolar,Bolivar,facturaActual.getFacturaSaldo());
        return MonedaUtil.formatoBs.format(saldobs.getValor());
    }

    public static Integer getSeleccionBusqueda() {
        return seleccionBusqueda;
    }

    public static void setSeleccionBusqueda(Integer seleccionBusqueda) {
        Contexto.seleccionBusqueda = seleccionBusqueda;
    }
}
