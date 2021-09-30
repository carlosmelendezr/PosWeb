package modelos.factura;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class FacturaTotal {

    private Moneda montoBase;
    private Moneda montoImpuesto;
    private Moneda montoDescuento;
    private Moneda montoTotal;
    private Moneda totalPago;
    private Moneda totalSaldo;
    private TipoMoneda tipoMoneda;

    private void asignaMonedaDefault() {
        /* Poneda por Defecto USD$ */
        TipoMoneda Dolar = new TipoMoneda(1,"USD","DOLAR","$",new Moneda("1"),MonedaUtil.formatoUsd);
        this.tipoMoneda = Dolar;
    }

    private void inicializa() {
        this.montoBase = new Moneda("0");
        this.montoImpuesto = new Moneda("0");
        this.montoDescuento = new Moneda("0");
        this.montoTotal = new Moneda("0");
        this.totalPago = new Moneda("0");
        this.totalSaldo = new Moneda("0");
    }

    FacturaTotal() {
        asignaMonedaDefault();
        inicializa();
    }

    FacturaTotal(TipoMoneda mon) {
        if (mon==null) {
            asignaMonedaDefault();
        }
        this.tipoMoneda = mon;
        inicializa();
    }

    public FacturaTotal ClonarMontos(FacturaTotal fuente) {
        FacturaTotal Clon = new FacturaTotal();
        Clon.setMontoBase(this.montoBase);
        Clon.setMontoImpuesto(this.montoImpuesto);
        Clon.setMontoDescuento(this.montoDescuento);
        Clon.setMontoTotal(this.montoTotal);
        Clon.setTotalPago(this.totalPago);

        return Clon;
    }

    public FacturaTotal Covertir( TipoMoneda destino ) {
        FacturaTotal Total = ClonarMontos(this);

        Total.montoBase.multiplicar( destino.getTasacambio() );
        Total.montoImpuesto.multiplicar(destino.getTasacambio());
        Total.montoDescuento.multiplicar(destino.getTasacambio());
        Total.montoTotal.multiplicar(destino.getTasacambio());
        Total.totalPago.multiplicar(destino.getTasacambio());
        Total.setTipoMoneda(destino);

        return Total;
    }

    public void actualizar(List<LineaFactura> lineas) {
        inicializa();
        for (LineaFactura lin:lineas) {
            Moneda addmonto = new Moneda(lin.getPreciobase());
            addmonto.setPrecision(new MathContext(2, RoundingMode.HALF_UP));
            //addmonto.redondear();
            Moneda cant = new Moneda(lin.getCantidad());
            cant.setPrecision(new MathContext(2, RoundingMode.HALF_UP));
            System.out.println("Monto Base "+addmonto.getValor().toString());
            addmonto.multiplicar(cant);

            System.out.println("Monto Base x cantidad "+addmonto.getValor().toString());

            this.montoBase.sumar( addmonto );

            Moneda imp = new Moneda(addmonto);
            Moneda alicuota = lin.getProducto().getAlicuota();
            alicuota.setPrecision(new MathContext(2, RoundingMode.HALF_UP));
            imp.multiplicar(alicuota);
            imp.dividir(new Moneda("100"));
            this.montoImpuesto.sumar(imp);
            System.out.println("Monto Impuesto "+imp.getValor().toString());


        }
        totalizar();
        System.out.println("Total "+getMontoTotal().getValor().toString());

    }


    public void totalizar() {

        this.montoTotal.setValor( this.montoBase.getValor());
        this.montoTotal.sumar(this.montoImpuesto);
        this.totalSaldo.setValor(montoTotal.getValor());
        this.totalSaldo.restar(this.totalPago);

    }


    public void imprimirTotal() {
        totalizar();
        System.out.println("- - - - - MONEDA : "+this.tipoMoneda.getCodmoneda()+" -> "+this.tipoMoneda.getDescripcion()+
                " Tasa de Cambio :"+this.tipoMoneda.getValorFormato(this.tipoMoneda.getTasacambio())+
                " - - - - - -");

        System.out.println("Monto Base   : "+this.tipoMoneda.getValorFormato(this.montoBase)+" "+this.tipoMoneda.getSimbolo());
        System.out.println("Monto IVA    : "+this.tipoMoneda.getValorFormato(this.montoImpuesto)+" "+this.tipoMoneda.getSimbolo() );
        System.out.println("Total        : "+this.tipoMoneda.getValorFormato(this.montoTotal)+" "+this.tipoMoneda.getSimbolo() );
        System.out.println("Total Pagado : "+this.tipoMoneda.getValorFormato(this.totalPago)+" "+this.tipoMoneda.getSimbolo() );
        System.out.println("Total Saldo  : "+this.tipoMoneda.getValorFormato(this.totalSaldo)+" "+this.tipoMoneda.getSimbolo() );
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - -  -");
    }

    public String montoTotalFormato() {
        return this.tipoMoneda.getValorFormato(this.montoTotal);
    }

    public void setTotalPago(Moneda totalPago) {
        this.totalPago = totalPago;
        totalizar();
    }

    public Moneda getTotalSaldo() {
        return totalSaldo;
    }

    public Moneda getMontoBase() {
        return montoBase;
    }

    public void setMontoBase(Moneda montoBase) {
        this.montoBase = montoBase;
    }

    public Moneda getMontoImpuesto() {
        return montoImpuesto;
    }

    public void setMontoImpuesto(Moneda montoImpuesto) {
        this.montoImpuesto = montoImpuesto;
    }

    public Moneda getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(Moneda montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    public Moneda getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Moneda montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Moneda getTotalPago() {
        return totalPago;
    }

    public void setTotalSaldo(Moneda totalSaldo) {
        this.totalSaldo = totalSaldo;
    }

    public TipoMoneda getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(TipoMoneda tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }
}
