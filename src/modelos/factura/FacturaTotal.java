package modelos.factura;

import java.math.BigDecimal;

public class FacturaTotal {

    private BigDecimal montoBase;
    private BigDecimal montoImpuesto;
    private BigDecimal montoDescuento;
    private BigDecimal montoTotal;
    private BigDecimal totalPago;
    private BigDecimal totalSaldo;
    private Moneda moneda;

    private void asignaMonedaDefault() {
        /* Poneda por Defecto USD$ */
        Moneda Dolar = new Moneda(1,"USD","DOLAR","$",new BigDecimal("1"),MonedaUtil.formatoUsd);
        this.moneda = Dolar;
    }

    private void inicializa() {
        this.montoBase = new BigDecimal("0");
        this.montoImpuesto = new BigDecimal("0");
        this.montoDescuento = new BigDecimal("0");
        this.montoTotal = new BigDecimal("0");
        this.totalPago = new BigDecimal("0");
        this.totalSaldo = new BigDecimal("0");
    }

    FacturaTotal() {
        asignaMonedaDefault();
        inicializa();
    }

    FacturaTotal(Moneda mon) {
        if (mon==null) {
            asignaMonedaDefault();
        }
        this.moneda = mon;
        inicializa();
    }

    public FacturaTotal Covertir( Moneda destino ) {
        FacturaTotal Total = new FacturaTotal(destino);

        Total.setMontoBase(this.montoBase.multiply( destino.getTasacambio() ));
        Total.setMontoImpuesto(this.montoImpuesto.multiply(destino.getTasacambio()));
        Total.setMontoDescuento(this.montoDescuento.multiply( destino.getTasacambio()));
        Total.setMontoTotal(this.montoTotal.multiply( destino.getTasacambio()));
        Total.setTotalPago(this.totalPago.multiply( destino.getTasacambio()));
        //Total.setTotalSaldo(this.totalSaldo * destino.getTasacambio());

        return Total;
    }



    public void agregarMonto(LineaFactura linea) {
        BigDecimal addmonto = linea.getProducto().getPrecio().multiply(BigDecimal.valueOf(linea.getCantidad()));


        this.montoBase = this.montoBase.add( addmonto );

        BigDecimal Imp = this.montoBase.multiply(linea.getProducto().getAlicuota());
        Imp = Imp.divide(new BigDecimal("100"));

        this.montoImpuesto = this.montoImpuesto.add(Imp);

        totalizar();


    }


    public void totalizar() {
        this.montoTotal = this.montoBase.add(this.montoImpuesto);
        this.totalSaldo = this.montoTotal.subtract(this.totalPago);
    }


    public void imprimirTotal() {
        totalizar();
        System.out.println("- - - - - MONEDA : "+this.moneda.getCodmoneda()+" -> "+this.moneda.getDescripcion()+
                " Tasa de Cambio :"+this.moneda.getValorFormato(this.moneda.getTasacambio())+
                " - - - - - -");

        System.out.println("Monto Base   : "+this.moneda.getValorFormato(this.montoBase)+" "+this.moneda.getSimbolo());
        System.out.println("Monto IVA    : "+this.moneda.getValorFormato(this.montoImpuesto)+" "+this.moneda.getSimbolo() );
        System.out.println("Total        : "+this.moneda.getValorFormato(this.montoTotal)+" "+this.moneda.getSimbolo() );
        System.out.println("Total Pagado : "+this.moneda.getValorFormato(this.totalPago)+" "+this.moneda.getSimbolo() );
        System.out.println("Total Saldo  : "+this.moneda.getValorFormato(this.totalSaldo)+" "+this.moneda.getSimbolo() );
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - -  -");
    }

    public BigDecimal getMontoBase() {
        return montoBase;
    }

    public void setMontoBase(BigDecimal montoBase) {
        this.montoBase = montoBase;
    }

    public BigDecimal getMontoImpuesto() {
        return montoImpuesto;
    }

    public void setMontoImpuesto(BigDecimal montoImpuesto) {
        this.montoImpuesto = montoImpuesto;
    }

    public BigDecimal getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(BigDecimal montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public BigDecimal getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(BigDecimal totalPago) {
        this.totalPago = totalPago;
    }

    public BigDecimal getTotalSaldo() {
        return totalSaldo;
    }

    public void setTotalSaldo(BigDecimal totalSaldo) {
        this.totalSaldo = totalSaldo;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
}
