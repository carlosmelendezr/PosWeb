package modelos.factura;

public class LineaFactura {
    public static Integer ESTATUS_OK = 0;
    public static Integer ESTATUS_ANULADO = 1;

    private Integer  id;
    private Producto producto;
    private Double   cantidad;
    private String   codigo;
    private String   referencia;
    private String   descripcion;
    private Moneda   precio;
    private Moneda   descuento;
    private Moneda   total;
    private String   precioFormato;
    private String   totalFormato;
    private Integer  estatus;



    public LineaFactura(Integer id, Producto producto, Double cantidad) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.descripcion = producto.getDescripcion();
        this.codigo = producto.getCodigo();
        this.referencia = producto.getReferencia();
        this.precio = producto.getPrecio();
        this.descuento = new Moneda(0);
        this.total = new Moneda("0");
        this.estatus = ESTATUS_OK;

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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioFormato(String precioFormato) {
        this.precioFormato = precioFormato;
    }

    public String getPrecioFormato() {
        precioFormato = MonedaUtil.formatoUsd.format( precio.getValor());
        return precioFormato;
    }

    public String getTotalFormato() {
        getTotal();
        totalFormato = MonedaUtil.formatoUsd.format( total.getValor());
        return totalFormato;
    }

    public Moneda getTotal() {
        System.out.println("Precio = "+this.precio.getValor() +" Cantidad = "+this.cantidad);
        this.total.setValor(this.precio.getValor());
        this.total.multiplicar(new Moneda(this.cantidad));
        return total;
    }

    public void setTotal(Moneda total) {
        this.total = total;
    }

    public void setTotalFormato(String totalFormato) {
        this.totalFormato = totalFormato;
    }
}
