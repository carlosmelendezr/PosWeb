package modelos.factura;

public class ProductoBuscar {
    private String descripcion;
    private String codigo;
    private String ref;

    public ProductoBuscar(String descripcion, String codigo, String ref) {
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.ref = ref;
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
