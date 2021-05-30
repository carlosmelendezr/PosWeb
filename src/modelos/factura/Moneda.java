package modelos.factura;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Moneda {
    private Integer id;
    private String  codmoneda;
    private String  descripcion;
    private String  simbolo;
    //private Double  valor;
    private BigDecimal tasacambio;
    private Boolean esMonedaBase;
    private  DecimalFormat formato;



    public Moneda(Integer id, String codmoneda, String descripcion, String simbolo,
                  BigDecimal tasacambio, DecimalFormat formato ) {
        this.id = id;
        this.codmoneda = codmoneda;
        this.descripcion = descripcion;
        this.simbolo = simbolo;
        this.tasacambio = tasacambio;
        this.formato = formato;
        this.esMonedaBase = false;
        //this.valor = 0.0;
    }

    public String getValorFormato(BigDecimal monto) {
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

    public BigDecimal getTasacambio() {
        return tasacambio;
    }

    public void setTasacambio(BigDecimal tasacambio) {
        this.tasacambio = tasacambio;
    }



    public DecimalFormat getFormato() {
        return formato;
    }

    public void setFormato(DecimalFormat formato) {
        this.formato = formato;
    }

    public Boolean getEsMonedaBase() {
        return esMonedaBase;
    }

    public void setEsMonedaBase(Boolean esMonedaBase) {
        this.esMonedaBase = esMonedaBase;
    }
}
