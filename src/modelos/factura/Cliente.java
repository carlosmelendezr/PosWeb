package modelos.factura;

import java.util.List;

public class Cliente {

    private Integer id;
    private String  razonsocial;
    private Integer rif;
    private String  tiporif;
    private List<Direccion> direcciones;
    private List<Telefono>  telefonos;
    private String correo;

    Cliente(Integer id, String razonsocial, Integer rif,
            String tiporif,List<Direccion> direcciones,List<Telefono> telefonos ) {
        this.id = id;
        this.razonsocial = razonsocial;
        this.rif = rif;
        this.tiporif = tiporif;
        this.direcciones = direcciones;
        this.telefonos = telefonos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public Integer getRif() {
        return rif;
    }

    public void setRif(Integer rif) {
        this.rif = rif;
    }

    public String getTiporif() {
        return tiporif;
    }

    public void setTiporif(String tiporif) {
        this.tiporif = tiporif;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
