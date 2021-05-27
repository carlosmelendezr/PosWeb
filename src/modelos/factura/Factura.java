package modelos.factura;

import java.util.ArrayList;
import java.util.List;

public class Factura  {
    private Integer id;
    private Integer numeroFactura;
    private Integer numeroCaja;
    private DatosFiscales datosfiscales;
    private Cliente cliente;
    private List<Pago> pago;
    private List<LineaFactura> lineas;
    private FacturaTotal totales;


    private Boolean Imprimible;
    private Boolean Pagada;
    private Boolean Cancelada;
    private Boolean Error;
    private Boolean Espera;

    private void Inicializa() {

        this.pago = new ArrayList<>();
        this.lineas = new ArrayList<>();
        this.totales = new FacturaTotal();
        this.totales = new FacturaTotal();

        Imprimible = false;
        Pagada = false;
        Cancelada = false;
        Error = false;
        Espera = false;

    }


    Factura() {
        Inicializa();
    }

    Factura(Moneda mon) {
        this.totales = new FacturaTotal(mon);
        Inicializa();
    }


    public void agregarLinea(LineaFactura linea) {
        if (linea == null) {
            return;
        }
        this.lineas.add(linea);
        totales.agregarMonto(linea);
    }


    public void asignarCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public void agregarPago(Pago pago) {
        this.pago.add(pago);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Integer numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Integer getNumeroCaja() {
        return numeroCaja;
    }

    public void setNumeroCaja(Integer numeroCaja) {
        this.numeroCaja = numeroCaja;
    }

    public DatosFiscales getDatosfiscales() {
        return datosfiscales;
    }

    public void setDatosfiscales(DatosFiscales datosfiscales) {
        this.datosfiscales = datosfiscales;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Pago> getPago() {
        return pago;
    }

    public void setPago(List<Pago> pago) {
        this.pago = pago;
    }

    public List<LineaFactura> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaFactura> lineas) {
        this.lineas = lineas;
    }

    private void actualizarPago() {
        

    }



    public FacturaTotal getTotales() {
        return totales;
    }

    public void setTotales(FacturaTotal totales) {
        this.totales = totales;
    }


    public Boolean getImprimible() {
        return Imprimible;
    }

    public void setImprimible(Boolean imprimible) {
        Imprimible = imprimible;
    }

    public Boolean getPagada() {
        return Pagada;
    }

    public void setPagada(Boolean pagada) {
        Pagada = pagada;
    }

    public Boolean getCancelada() {
        return Cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        Cancelada = cancelada;
    }

    public Boolean getError() {
        return Error;
    }

    public void setError(Boolean error) {
        Error = error;
    }

    public Boolean getEspera() {
        return Espera;
    }

    public void setEspera(Boolean espera) {
        Espera = espera;
    }
}
