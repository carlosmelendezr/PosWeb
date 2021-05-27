package modelos.factura;

import java.util.List;

public class Producto {
    private Integer id;
    private Integer idTipoProducto;
    private String  descripcion;
    private String  referencia;
    private String  refprov;
    private String  imagenurl;
    private Integer idImpuesto;
    private Integer idCategoria;
    private Integer idMarca;
    private String  unMedida;
    private Double  precio;
    private Double  costo;
    private Double  alicuota;
    private Double  stock;
    private Integer idProveedor;
    private List<String> codbarra;

   Producto(Integer id,String descripcion,
                        String referencia,
                        Double precio,Double alicuota,List<String> codbarra ) {
       this.id = id;
       this.descripcion = descripcion;
       this.referencia = referencia;
       this.precio = precio;
       this.alicuota = alicuota;
       this.codbarra = codbarra;
   }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Integer idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getRefprov() {
        return refprov;
    }

    public void setRefprov(String refprov) {
        this.refprov = refprov;
    }

    public String getImagenurl() {
        return imagenurl;
    }

    public void setImagenurl(String imagenurl) {
        this.imagenurl = imagenurl;
    }

    public Integer getIdImpuesto() {
        return idImpuesto;
    }

    public void setIdImpuesto(Integer idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getUnMedida() {
        return unMedida;
    }

    public void setUnMedida(String unMedida) {
        this.unMedida = unMedida;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Double getAlicuota() {
        return alicuota;
    }

    public void setAlicuota(Double alicuota) {
        this.alicuota = alicuota;
    }

    public List<String> getCodbarra() {
        return codbarra;
    }

    public void setCodbarra(List<String> codbarra) {
        this.codbarra = codbarra;
    }
}
