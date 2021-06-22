package servicios.impresion;

import modelos.factura.Moneda;

import java.util.List;

public class LineaItemBixolon
        implements LineaItem {

    private Moneda precio;
    private Double cantidad;
    private String codigo;
    private String descripcion;
    private List<Integer> tasas;
    private Moneda tasa;


    private Integer MaxLongPrecio;
    private Integer MaxLongCantidad;
    private Integer MaxLongCodigo;
    private Integer MaxLongDescripcion;

    private String Comando;

    @Override
    public String armarComando() {
        StringBuilder comm = new StringBuilder();


        return null;
    }

    public Moneda getPrecio() {
        return precio;
    }

    public void setPrecio(Moneda precio) {
        this.precio = precio;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Integer> getTasas() {
        return tasas;
    }

    public void setTasas(List<Integer> tasas) {
        this.tasas = tasas;
    }

    public Integer getMaxLongPrecio() {
        return MaxLongPrecio;
    }

    public void setMaxLongPrecio(Integer maxLongPrecio) {
        MaxLongPrecio = maxLongPrecio;
    }

    public Integer getMaxLongCantidad() {
        return MaxLongCantidad;
    }

    public void setMaxLongCantidad(Integer maxLongCantidad) {
        MaxLongCantidad = maxLongCantidad;
    }

    public Integer getMaxLongCodigo() {
        return MaxLongCodigo;
    }

    public void setMaxLongCodigo(Integer maxLongCodigo) {
        MaxLongCodigo = maxLongCodigo;
    }

    public Integer getMaxLongDescripcion() {
        return MaxLongDescripcion;
    }

    public void setMaxLongDescripcion(Integer maxLongDescripcion) {
        MaxLongDescripcion = maxLongDescripcion;
    }

    public String getComando() {
        return Comando;
    }

    public void setComando(String comando) {
        Comando = comando;
    }

    public Moneda getTasa() {
        return tasa;
    }

    public void setTasa(Moneda tasa) {
        this.tasa = tasa;
    }


}
