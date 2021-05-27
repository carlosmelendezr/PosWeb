package modelos.factura;

import java.text.DecimalFormat;

public class Moneda {
    Integer id;
    String  codmoneda;
    String  descripcion;
    String  simbolo;
    Double  valor;
    Double  tasacambio;
    DecimalFormat formato;



    public Moneda(Integer id, String codmoneda, String descripcion, String simbolo,
                  Double tasacambio,DecimalFormat formato ) {
        this.id = id;
        this.codmoneda = codmoneda;
        this.descripcion = descripcion;
        this.simbolo = simbolo;
        this.tasacambio = tasacambio;
        this.formato = formato;
    }

    public String getValorFormato(Double monto) {
        return formato.format( monto );
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodmoneda() {
        return codmoneda;
    }

    public void setCodmoneda(String codmoneda) {
        this.codmoneda = codmoneda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public Double getTasacambio() {
        return tasacambio;
    }

    public void setTasacambio(Double tasacambio) {
        this.tasacambio = tasacambio;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public DecimalFormat getFormato() {
        return formato;
    }

    public void setFormato(DecimalFormat formato) {
        this.formato = formato;
    }
}
