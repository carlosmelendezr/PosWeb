package modelos.factura;

import modelos.datos.Operaciones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
    private TipoMoneda tipoMoneda;
    private Calendar fecha;

    private Boolean Activa;
    private Boolean Imprimible;
    private Boolean Pagada;
    private Boolean Cancelada;
    private Boolean Error;
    private Boolean Espera;

    private String  Mensaje;

    private void Inicializa() {
        this.numeroFactura = 0;
        this.numeroCaja    = 0;
        this.fecha = Calendar.getInstance();

        this.pagos = new ArrayList<>();
        this.lineas = new ArrayList<>();
        this.totales = new FacturaTotal();

        Imprimible = false;
        Pagada = false;
        Cancelada = false;
        Error = false;
        Espera = false;
        Mensaje = "";

    }

    public void limpiarPagos() {
        this.pagos.clear();
        Operaciones.LimpiarPagoFactura(this.id);
        actualizarPago();
    }


    public Factura(TipoMoneda mon) {
        if (!mon.getEsMonedaBase()) {

            this.Error = true;
            this.Activa = false;
            this.Mensaje = "Se debe especificar una moneda con condicion de moneda base.";
            return;
        }
        this.Activa  = true;
        this.totales = new FacturaTotal(mon);
        this.tipoMoneda = mon;
        this.Activa  = true;
        Inicializa();

    }

    public Boolean GuardarPago(Pago pag) {
        return Operaciones.InsertarPagoFactura(pag,this.id);
    }

    public void actualizarLineas() {
        this.lineas = Operaciones.ObtenerLineasFactura(this.id);
        this.totales.actualizar(lineas);
    }

    public void obtenerPagos() {
        this.pagos = Operaciones.ObtenerPagosFactura(this);
        actualizarPago();

    }


    public void agregarLinea(LineaFactura linea) {
        if (linea == null) {
            return;
        }
        Operaciones.InsertarLineaFactura(linea,this.id);

        actualizarLineas();
        Operaciones.ActualizaEstatusFactura(this);


    }

    public void modificarLinea(int Index, LineaFactura linea) {
        if (Index >= 0 && Index <=this.lineas.size()) {
            this.lineas.set(Index,linea);
        }
    }

    public void eliminarLinea(int Index) {
        if (Index >= 0 && Index <= this.lineas.size()) {

            LineaFactura lineaact = lineas.get(Index);
            LineaFactura lineanue = lineas.get(Index);
            lineaact.setEstatus(LineaFactura.ESTATUS_ANULADO);
            lineas.set(Index,lineaact);

            lineanue.setCantidad(lineaact.getCantidad()*-1);
            agregarLinea((lineanue));
        }
    }



    public void asignarCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public String agregarPago(Pago pago) {
        String Mensaje = "";
        if (pago!=null) {
            Mensaje = "Agregando pago " + pago.getTipoMoneda().getCodmoneda() + " monto " + pago.getMontoformato();
            this.pagos.add(pago);
            GuardarPago(pago);
            actualizarPago();
        } else {
            Mensaje = "Pago no aceptado.";
        }
        return Mensaje;
    }


    public Moneda getTotalPagosPorTipo(TipoMoneda tipo) {
        Moneda total = new Moneda(0);
        for(Pago pag:pagos) {
            if (pag.getTipoMoneda().getCodmoneda().equals(tipo.getCodmoneda())) {
                total.sumar(pag.getTotal());
            }
        }
        return total;
    }

    public Moneda getTotalPagos() {
        Moneda total = new Moneda(0);
        for(Pago pag:pagos) {
            total.sumar(pag.getMontoMonedaBase());
        }
        return total;
    }

    public Moneda getFacturaSaldo() {
        return this.totales.getTotalSaldo();
    }


    private void actualizarPago() {
        Moneda totalpago = new Moneda("0");

        for (Pago pago : pagos)
        {

            if (pago.getTipoMoneda().getCodmoneda().equals(this.tipoMoneda.getCodmoneda())) {
                /*System.out.println( "Registrando pago "+pago.getTipoMoneda().getCodmoneda()+" monto "+
                        pago.getTotal().getValor());*/

                totalpago.sumar(pago.getTotal());
            }else {
                /*System.out.println("Convirtiendo de "+pago.getTipoMoneda().getCodmoneda()+" ->"+
                        this.tipoMoneda.getCodmoneda()+" "+pago.getTotal().getValor());*/
                //this.moneda.setValor(pago.getTotal());

                Moneda resul = MonedaUtil.ConvertirValor(pago.getTipoMoneda(), this.tipoMoneda,pago.getMonto());
                //System.out.println("               -> Resultado ="+this.tipoMoneda.getValorFormato(resul));

                totalpago.sumar(resul)  ;
            }

        }
        //System.out.println("Total pagos "+ pagos.size());
        this.totales.setTotalPago(totalpago);


        if (this.totales.getTotalSaldo().menorOigual(new Moneda("0")) ) {
            System.out.println("!!! La Factura es Imprimible !!!");
            this.Imprimible = true;
            this.Pagada = true;

        }
    }

    public boolean Finalizar() {
        boolean exito=false;
        if (this.Imprimible) {

        }

        this.Activa = false;

        Operaciones.ActualizaEstatusFactura(this);

        for (LineaFactura lin:lineas) {

            MovInventario mov = new MovInventario();
            mov.setCantidad(lin.getCantidad()*-1);
            mov.setIdproducto(lin.getProducto().getId());
            mov.setIdtipomov(3);
            mov.setFecha(this.fecha);
            Operaciones.insertarMovimientoInventario(mov);
        }
        lineas.clear();
        Inicializa();


        exito = true;

        return exito;
    }

    private boolean Imprimir() {
        boolean exito=false;
        return exito;
    }

    //<editor-fold desc="Getter and Setters">


    public FacturaTotal getTotales() {
        totales.totalizar();
        return totales;
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

    public TipoMoneda getMoneda() {
        return tipoMoneda;
    }

    public Boolean getActiva() {
        return Activa;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public TipoMoneda getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(TipoMoneda tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public void setActiva(Boolean activa) {
        Activa = activa;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    //</editor-fold>
}
