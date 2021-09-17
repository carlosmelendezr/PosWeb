package modelos.factura;

import java.util.Calendar;
import java.util.Date;

public class MovInventario {
    private Integer id;
    private Integer idtipomov;
    private Integer idproducto;
    private Double  cantidad;
    private Calendar fecha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdtipomov() {
        return idtipomov;
    }

    public void setIdtipomov(Integer idtipomov) {
        this.idtipomov = idtipomov;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
}
