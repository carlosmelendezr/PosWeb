package modelos.factura;

public class LineaFactura {
    private Integer id;

    private Producto producto;

    private Double  cantidad;



    public LineaFactura(Integer id, Producto producto, Double cantidad) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;

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



}
