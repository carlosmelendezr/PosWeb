package modelos.factura;

public class ProductoBuscar {
    private Integer id;
    private String descripcion;
    private String codigo;
    private String ref;

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
}
