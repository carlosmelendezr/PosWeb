package servicios.impresion;

import modelos.factura.Moneda;

import java.util.List;

public class LineaItemBixolon
        implements LineaItem {

    private Moneda precio;
    private Double cantidad;
    private String codigo;
    private String descripcion;
    private List<TasaImpresora> tasas;
    private Moneda tasa;


    private Integer MaxLongPrecio;
    private Integer MaxLongCantidad;
    private Integer MaxLongCodigo;
    private Integer MaxLongDescripcion;

    private String Comando;

    public LineaItemBixolon(List<TasaImpresora> tasas) {
        this.tasas = tasas;
    }

    @Override
    public String armarComando() {
        StringBuilder comm = new StringBuilder();
        for(TasaImpresora tas:tasas) {

            if (this.tasa.getValor().equals(tas.getTasa().getValor())) {

                comm.append(tas.getComando());

            }
        }
        String sPrecio = Util.llenarCeros(this.precio,this.MaxLongPrecio);
        comm.append(sPrecio);
        String sCantidad = Util.llenarCeros(this.cantidad,
                ImpBixolonSRP812.maximo_Digitos_Cantidad_Enteros,
                ImpBixolonSRP812.maximo_Digitos_Cantidad_Decimales);
        comm.append(sCantidad);
        comm.append("|"+this.codigo.trim()+"|");
        comm.append(this.descripcion);

        System.out.println("Agregar Item :"+comm);

        return comm.toString();
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
        this.codigo = codigo.replaceAll("[^\\.A-Za-z0-9_ ]", "");
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion.replaceAll("[^\\.A-Za-z0-9_ ]", "");
    }

    public List<TasaImpresora> getTasas() {
        return tasas;
    }

    public void setTasas(List<TasaImpresora> tasas) {
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
