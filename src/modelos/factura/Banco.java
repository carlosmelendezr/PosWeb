package modelos.factura;

public class Banco {
    private Integer id;
    private String  codigo;
    private String  descripcion;
    private Integer activo;

    public Banco() {
        this.id = 0;
        this.codigo = "";
        this.descripcion = "";
        this.activo=1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}
