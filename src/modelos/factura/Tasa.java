package modelos.factura;

import java.util.Calendar;

public class Tasa {
    private Integer id;
    private Moneda valor;
    private Calendar fecha;

    public Tasa() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tasa(Moneda valor, Calendar fecha) {
        this.valor = valor;
        this.fecha = fecha;
    }

    public Moneda getValor() {
        return valor;
    }

    public void setValor(Moneda valor) {
        this.valor = valor;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
}
