package modelos.factura;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class TipoMoneda {
    private Integer id;
    private String  codmoneda;
    private String  descripcion;
    private String  simbolo;
    private Moneda tasacambio;
    private Boolean esMonedaBase;
    private  DecimalFormat formato;
    private int precision;



    public TipoMoneda(Integer id, String codmoneda, String descripcion, String simbolo,
                      Moneda tasacambio, DecimalFormat formato ) {
        this.id = id;
        this.codmoneda = codmoneda;
        this.descripcion = descripcion;
        this.simbolo = simbolo;
        this.tasacambio = tasacambio;
        this.formato = formato;
        this.esMonedaBase = false;
        this.precision = Moneda.precisionDefault;

    }

    public TipoMoneda(Integer id, String codmoneda, String descripcion, String simbolo,
                      Moneda tasacambio, DecimalFormat formato, int precision ) {
        this.id = id;
        this.codmoneda = codmoneda;
        this.descripcion = descripcion;
        this.simbolo = simbolo;
        this.tasacambio = tasacambio;
        this.formato = formato;
        this.esMonedaBase = false;
        this.precision = precision;

    }

    public String getValorFormato(Moneda monto) {
        return formato.format( monto.getValor() );
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

    public Moneda getTasacambio() {
        return tasacambio;
    }

    public void setTasacambio(Moneda tasacambio) {
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

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }
}
