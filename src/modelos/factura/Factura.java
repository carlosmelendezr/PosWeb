package modelos.factura;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Factura  {
    private Integer id;
    private Integer numeroFactura;
    private Integer numeroCaja;
    private DatosFiscales datosfiscales;
    private Cliente cliente;
    private List<Pago> pagos;
    private List<LineaFactura> lineas;
    private FacturaTotal totales;
    private Moneda moneda;

    private Boolean Activa;
    private Boolean Imprimible;
    private Boolean Pagada;
    private Boolean Cancelada;
    private Boolean Error;
    private Boolean Espera;

    private String  Mensaje;

    private void Inicializa() {

        this.pagos = new ArrayList<>();
        this.lineas = new ArrayList<>();
        this.totales = new FacturaTotal();
        this.totales = new FacturaTotal();

        Imprimible = false;
        Pagada = false;
        Cancelada = false;
        Error = false;
        Espera = false;
        Mensaje = "";

    }


    Factura(Moneda mon) {
        if (!mon.getEsMonedaBase()) {

            this.Error = true;
            this.Activa = false;
            this.Mensaje = "Se debe especificar una moneda con condicion de moneda base.";
            return;
        }
        this.Activa  = true;
        this.totales = new FacturaTotal(mon);
        this.moneda  = mon;
        this.Activa  = true;
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
        System.out.println( "Agregando pago "+pago.getMoneda().getCodmoneda()+" monto "+pago.getMonto());
        this.pagos.add(pago);
        actualizarPago();
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

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public List<LineaFactura> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaFactura> lineas) {
        this.lineas = lineas;
    }

    private void actualizarPago() {
        BigDecimal totalpago = new BigDecimal("0");

        for (Pago pago : pagos)
        {

            if (pago.getMoneda().getCodmoneda().equals(this.moneda.getCodmoneda())) {
                System.out.println( "Registrando pago "+pago.getMoneda().getCodmoneda()+" monto "+pago.getTotal());

                totalpago = totalpago.add(pago.getTotal());
            }else {
                System.out.println("Convirtiendo de "+pago.getMoneda().getCodmoneda()+" ->"+this.moneda.getCodmoneda()+" "+pago.getTotal());
                //this.moneda.setValor(pago.getTotal());

                BigDecimal resul = MonedaUtil.ConvertirValor(pago.getMoneda(), this.moneda ,pago.getTotal());
                System.out.println("               -> Resultado ="+this.moneda.getValorFormato(resul));

                totalpago = totalpago.add(resul)  ;
            }

        }
        System.out.println("Total pagos "+ pagos.size());
        this.totales.setTotalPago(totalpago);
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

    public Moneda getMoneda() {
        return moneda;
    }

    public Boolean getActiva() {
        return Activa;
    }

    public String getMensaje() {
        return Mensaje;
    }
}
