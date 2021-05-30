package modelos.factura;

import java.math.BigDecimal;
import java.util.Date;

public class Pago {
    private Integer id;
    private Moneda  moneda;
    private BigDecimal monto;
    private String  referencia;
    private Banco   banco;
    private BigDecimal vuelto;
    private BigDecimal total;
    private Date    fechapago;
    private Date    fechareg;

    public Pago(Moneda moneda, BigDecimal monto, BigDecimal vuelto) {
        this.moneda = moneda;
        this.monto = monto;
        this.vuelto = vuelto;
        this.total = monto.subtract(vuelto);


    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public BigDecimal getVuelto() {
        return vuelto;
    }

    public void setVuelto(BigDecimal vuelto) {
        this.vuelto = vuelto;
    }

    public Date getFechapago() {
        return fechapago;
    }

    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }

    public Date getFechareg() {
        return fechareg;
    }

    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
