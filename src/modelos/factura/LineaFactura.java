package modelos.factura;

public class LineaFactura {
    private Integer  id;
    private Producto producto;
    private Double   cantidad;
    private String   codbarra;
    private String   referencia;
    private Moneda   precio;
    private Moneda   descuento;



    public LineaFactura(Integer id, Producto producto, Double cantidad, String codbarra) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.codbarra = codbarra;
        this.referencia = producto.getReferencia();
        this.precio = producto.getPrecio();

    }

    public void aplicarDescuento(Moneda desc) {
        this.descuento = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodbarra() {
        return codbarra;
    }

    public void setCodbarra(String codbarra) {
        this.codbarra = codbarra;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Moneda getPrecio() {
        return precio;
    }

    public void setPrecio(Moneda precio) {
        this.precio = precio;
    }

    public Moneda getDescuento() {
        return descuento;
    }

    public void setDescuento(Moneda descuento) {
        this.descuento = descuento;
    }
}
