package modelos.factura;

public class ProductoBuscar {
    private Integer id;
    private String descripcion;
    private String codigo;
    private String ref;
    private Moneda  precio;
    private String   precioFormato;

    public ProductoBuscar(Integer id,String descripcion, String codigo, String ref) {
        this.id = id;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.ref = ref;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Moneda getPrecio() {
        return precio;
    }

    public void setPrecio(Moneda precio) {
        this.precio = precio;
    }

    public String getPrecioFormato() {
        precioFormato = MonedaUtil.formatoUsd.format( precio.getValor());
        return precioFormato;
    }
}
