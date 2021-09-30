package modelos.factura;

import java.math.BigDecimal;
import java.util.List;

public class Producto {
    private Integer id;
    private Integer idTipoProducto;
    private String  descripcion;
    private String  codigo;
    private String  referencia;
    private String  refprov;
    private String  imagenurl;
    private Integer idImpuesto;
    private Integer idCategoria;
    private Integer idMarca;
    private String  unMedida;
    private Moneda precio;
    private Moneda costo;
    private Moneda  alicuota;
    private Double  stock;
    private Integer idProveedor;
    private List<String> codbarra;

    private String precioFormato;
    private String costoFormato;

    public Producto() {

    }

    public String getPrecioFormato() {
        precioFormato = MonedaUtil.formatoUsd.format( precio.getValor());
        return precioFormato;
    }

    public String getCostoFormato() {
        costoFormato = MonedaUtil.formatoUsd.format( costo.getValor());
        return costoFormato;
    }


    Producto(Integer id, String descripcion,
             String referencia,
             Moneda precio, Moneda alicuota, List<String> codbarra ) {
       this.id = id;
       this.descripcion = descripcion;
       this.referencia = referencia;
       this.alicuota = alicuota;
       this.precio = precio;
       this.alicuota = alicuota;
       this.codbarra = codbarra;

   }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public List<String> getCodbarra() {
        return codbarra;
    }

    public void setCodbarra(List<String> codbarra) {
        this.codbarra = codbarra;
    }

    public Moneda getPrecio() {
        return precio;
    }

    public void setPrecio(Moneda precio) {
        this.precio = precio;
    }

    public Moneda getCosto() {
        return costo;
    }

    public void setCosto(Moneda costo) {
        this.costo = costo;
    }

    public Moneda getAlicuota() {
        return alicuota;
    }

    public void setAlicuota(Moneda alicuota) {
        this.alicuota = alicuota;
    }


}
