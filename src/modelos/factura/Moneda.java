package modelos.factura;

import java.math.BigDecimal;
import java.math.MathContext;

public class Moneda {
    public static int precisionDefault = 4;
    private MathContext precision ;
    private BigDecimal  valor;


    public Moneda() {
        this.precision =  new MathContext(precisionDefault);
    }
    public Moneda(Moneda moneda) {
        this.precision =  moneda.getPrecision();
        this.valor = moneda.getValor();
    }

    public Moneda(BigDecimal valor) {
        this.precision =  new MathContext( precisionDefault );
        this.valor = valor;
    }
    public Moneda(BigDecimal valor, MathContext precision) {
        this.precision =  precision;
        this.valor = valor;
    }

    public Moneda(String valor) {
        this.precision =  new MathContext(precisionDefault);
        this.valor = new BigDecimal(valor);
    }
    public Moneda(String valor, MathContext precision) {
        this.precision = precision;

        this.valor = new BigDecimal(valor);
    }
    public Moneda(Double valor) {
        this.precision =  new MathContext(precisionDefault);
        this.valor = new BigDecimal(valor);
    }
    public Moneda(Integer valor) {
        this.precision =  new MathContext(precisionDefault);
        this.valor = new BigDecimal(valor);
    }

    public void sumar( Moneda valor ) {
        this.valor = this.valor.add(valor.getValor());
    }
    public void restar(Moneda valor ) {
        this.valor = this.valor.subtract(valor.getValor());
    }
    public void multiplicar(Moneda valor) {
        this.valor = this.valor.multiply(valor.getValor());
    }
    public void dividir(Moneda divisor) {
        if (precision==null) {
            this.precision =  new MathContext( precisionDefault );
        }
        try {

            this.valor = this.valor.divide(divisor.getValor(),precision);
        } catch (Exception e) {
            System.out.println("Error al dividir :"+this.valor+" Divisor = "+divisor.getValor()+" "+precision);
            System.out.println(e.getMessage());
        }
    }

    public Moneda Convertir(Moneda tasacambio) {
        Moneda nueva = new Moneda(this);
        nueva.multiplicar(tasacambio);
        System.out.println("Original="+this.getValor()+", Nueva ="+nueva.getValor());
        return nueva;
    }


    public boolean igualZero() {
        return (0 == this.valor.compareTo(BigDecimal.ZERO));
    }
    public boolean igual(Moneda y) {
        return (0 == this.valor.compareTo(y.getValor()));
    }
    public boolean menor( Moneda y) {
        return (-1 == this.valor.compareTo(y.getValor()));
    }
    public boolean menorOigual(Moneda y) {
        return (this.valor.compareTo(y.getValor()) <= 0);
    }
    public boolean mayor( Moneda y) {
        return (1 == this.valor.compareTo(y.getValor()));
    }
    public boolean mayorOigual( Moneda y) {
        return (this.valor.compareTo(y.getValor()) >= 0);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setValor(Double valor) {
        this.valor = BigDecimal.valueOf(valor);
    }
    public void setValor(Integer valor) {
        this.valor = BigDecimal.valueOf(valor);
    }

    public MathContext getPrecision() {
        return precision;
    }

    public void setPrecision(MathContext precision) {
        this.precision = precision;
    }
}
