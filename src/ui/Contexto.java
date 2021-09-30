package ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import modelos.datos.*;
import modelos.factura.*;
import servicios.impresion.BixolonServicios;
import servicios.impresion.DatosImpresora;
import servicios.impresion.ImpBixolonSRP812;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
    public static SimpleStringProperty totalArt = new SimpleStringProperty();
    public static SimpleStringProperty numeroFactura = new SimpleStringProperty();
    public static SimpleStringProperty tasaDolar = new SimpleStringProperty();
    public static Tasa TasaDia;
    public static Usuario usuarioActivo;
    public static Integer bancoSeleccionado;
    public static DatosImpresora impresora;


    public static void inicializar() {

        tasasImpuesto = new ArrayList<Moneda>(Arrays.asList(new Moneda(0),new Moneda(16.0)));

        resultadoBusqueda = observableArrayList();
        seleccionBusqueda = 0;

        facturaListaproductos = observableArrayList();
        facturaListapagos = observableArrayList();

        TasaDia = dboTasa.buscarUltimaTasa();
        impresora = dboDatosImpresora.cargarImpresora();

        tasaDolarHoy = new Moneda(TasaDia.getValor());
        MonedaUtil.inicializar();

        Dolar = new TipoMoneda(1,"USD","DOLAR","$",new Moneda("1"),MonedaUtil.formatoUsd);
        Dolar.setEsMonedaBase(true);
        Dolar.setPrecision(4);
        Bolivar = new TipoMoneda(2,"VES","BOLIVAR","Bs.",tasaDolarHoy, MonedaUtil.formatoBs);
        Bolivar.setPrecision(2);

        facturaActual = Operaciones.UltimaFacturaActiva();
        System.out.println();

        if (facturaActual==null) {

            facturaActual = Operaciones.CrearFactura(Dolar,TasaDia.getId());
            facturaActual.setIdTasa(TasaDia.getId());
        } else {

            if (facturaActual.getNumeroFactura()==0) {
                facturaActual.setNumeroFactura(facturaActual.getId());
            }
            facturaActual.obtenerLineas();
            facturaActual.obtenerPagos();
        }
        facturaListaproductos.addAll(facturaActual.getLineas());
        numeroFactura.set(facturaActual.getNumeroFactura().toString());
        tasaDolar.set(MonedaUtil.formatoBs.format(Contexto.Bolivar.getTasacambio().getValor()));
        facturaActual.setImpresora(impresora);

        actulizaTotales();

        if (validarImpresora()) {
            MensajeEstatus.set("Preparado - Impresora Configurada :" + impresora.getSerial() + " Puerto :" + impresora.getPuerto()+" Estatus : Conectada");

        } else {
            Acciones.dialogoAlerta("Error de conexión con la Impresora","La impresora no tiene conexión.");
        }

    }

    public static void enviarEstus(String Mensaje) {
        MensajeEstatus.set(Mensaje);
    }

    public static void agregarLineaFactura(LineaFactura lin) {

        if (facturaActual.agregarLinea(lin)) {
            facturaListaproductos.clear();
            facturaListaproductos.addAll(facturaActual.getLineas());
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
        String sArt = String.valueOf(facturaListaproductos.size());
        totalArt.set(sArt);

    }

    public static void actualizaPagos() {
        facturaListapagos.clear();
        List<Pago> lista = facturaActual.getPagos();
        for(Pago pag:lista) {
            facturaListapagos.add(pag);
        }
    }

    public static void finalizarFactura() {

        if (!validarImpresora()) {
            Acciones.dialogoAlerta("Error de conexión con la Impresora","La impresora no tiene conexión.");
            return;
        }
        facturaActual.FinalizarImprimir();
        reiniciarFactura();

    }

    public static void guardarFactura() {
        facturaActual.Finalizar();
        reiniciarFactura();
    }

    public static void reiniciarFactura() {
        facturaListaproductos.clear();
        facturaListaproductos.addAll(facturaActual.getLineas());

        facturaActual = Operaciones.CrearFactura(Dolar,TasaDia.getId());
        facturaActual.setNumeroFactura(facturaActual.getId());
        numeroFactura.set(facturaActual.getNumeroFactura().toString());
        facturaActual.setImpresora(impresora);

        actulizaTotales();
        enviarEstus("Factura guardada correctamente.");

    }

    public static void emitirReporteZ() {
        if (Acciones.dialogoConfirmar("Imprimir Reporte Z","Está seguro que desea imprimirlo?")) {

            ImpBixolonSRP812 Bixolon = new ImpBixolonSRP812();

            Bixolon.inicializar(impresora, Dolar, facturaActual.getNumeroFactura());
            Bixolon.cargarTablaComandos();
            Bixolon.ReporteZ();
            Bixolon.finalizar();
        }
    }

    public static void anularFactura() {

        if (facturaListaproductos.size()==0) {
            Acciones.dialogoAlerta("Anulación de Factura","La factura no tiene articulos para anular");
            return;
        }
        if (Acciones.dialogoConfirmar("Anulación de Factura","Está seguro que desea anularla?")) {

           facturaActual.Cancelar();
           facturaListaproductos.clear();
           facturaListaproductos.addAll(facturaActual.getLineas());
           facturaActual = Operaciones.CrearFactura(Dolar,TasaDia.getId());
           numeroFactura.set(facturaActual.getNumeroFactura().toString());
           facturaActual.setImpresora(impresora);
           actulizaTotales();


        }
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

    public static void agregarTasa() {
        if (facturaActual.getPagos().size()>0) {
            Acciones.dialogoAlerta("Agregar Tasa","La factura no debe tener pagos registrados");
            return;
        }

        Double valor = Acciones.dialogoTasa();
        if (valor > 0) {

            TasaDia.setValor(new Moneda(valor));
            TasaDia.setFecha(Calendar.getInstance());

            if (dboTasa.InsertarTasa(TasaDia)) {
                TasaDia = dboTasa.buscarUltimaTasa();
                Bolivar.setTasacambio(new Moneda(valor));
                tasaDolar.set(MonedaUtil.formatoBs.format(Contexto.Bolivar.getTasacambio().getValor()));
                facturaActual.setIdTasa(TasaDia.getId());
            }

        }

    }

    public static boolean validarImpresora() {
        boolean valida = false;
        BixolonServicios svr = new BixolonServicios();
        if (svr.abrirPuerto(impresora.getPuerto())) {
            if (svr.CheckPrinter()) {
                svr.cerrarPuerto();
                valida = true;
                System.out.println("Impresora OK");
            }
        }
        return valida;
    }
}
