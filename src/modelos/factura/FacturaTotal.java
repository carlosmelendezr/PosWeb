package modelos.factura;

public class FacturaTotal {

    private Double montoBase;
    private Double montoImpuesto;
    private Double montoDescuento;
    private Double montoTotal;
    private Double totalPago;
    private Moneda moneda;

    private void asignaMonedaDefault() {
        /* Poneda por Defecto USD$ */
        Moneda Dolar = new Moneda(1,"USD","DOLAR","$",1.0,MonedaUtil.formatoUsd);
        this.moneda = Dolar;
    }

    private void inicializa() {
        this.montoBase = 0.0;
        this.montoImpuesto = 0.0;
        this.montoDescuento = 0.0;
        this.montoTotal = 0.0;
        this.totalPago = 0.0;
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

        Total.setMontoBase(this.montoBase * destino.getTasacambio() );
        Total.setMontoImpuesto(this.montoImpuesto * destino.getTasacambio());
        Total.setMontoDescuento(this.montoDescuento * destino.getTasacambio());
        Total.setMontoTotal(this.montoTotal * destino.getTasacambio());
        Total.setTotalPago(this.totalPago * destino.getTasacambio());

        return Total;
    }



    public void agregarMonto(LineaFactura linea) {

        this.montoBase += linea.getCantidad() * linea.getProducto().getPrecio();
        this.montoImpuesto += (this.montoBase * linea.getProducto().getAlicuota()) /100;
        this.montoTotal = this.montoBase + this.montoImpuesto;

    }

    public Double getMontoBase() {
        return montoBase;
    }

    public void setMontoBase(Double montoBase) {
        this.montoBase = montoBase;
    }

    public Double getMontoImpuesto() {
        return montoImpuesto;
    }

    public void setMontoImpuesto(Double montoImpuesto) {
        this.montoImpuesto = montoImpuesto;
    }

    public Double getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(Double montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Double totalPago) {
        this.totalPago = totalPago;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public void imprimirTotal() {
        System.out.println("- - - - - MONEDA : "+this.moneda.getCodmoneda()+" -> "+this.moneda.getDescripcion()+
                " Tasa de Cambio :"+this.moneda.getValorFormato(this.moneda.getTasacambio())+
                " - - - - - -");

        System.out.println("Monto Base : "+this.moneda.getValorFormato(this.montoBase)+" "+this.moneda.getSimbolo());
        System.out.println("Monto IVA  : "+this.moneda.getValorFormato(this.montoImpuesto)+" "+this.moneda.getSimbolo() );
        System.out.println("Total      : "+this.moneda.getValorFormato(this.montoTotal)+" "+this.moneda.getSimbolo() );
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - -  -");


    }
}
