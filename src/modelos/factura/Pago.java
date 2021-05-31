package modelos.factura;

import java.math.BigDecimal;
import java.util.Date;

public class Pago {
    private Integer id;
    private TipoMoneda tipoMoneda;
    private Moneda monto;
    private String referencia;
    private Banco  banco;
    private Moneda vuelto;
    private Moneda total;
    private Date   fechapago;
    private Date   fechareg;

    public Pago(TipoMoneda tipoMoneda, Moneda monto, Moneda vuelto) {
        this.tipoMoneda = tipoMoneda;
        this.monto = monto;
        this.vuelto = vuelto;
        this.total =  monto;
        total.restar(vuelto);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoMoneda getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(TipoMoneda tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public Moneda getMonto() {
        return monto;
    }

    public void setMonto(Moneda monto) {
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

    public Moneda getVuelto() {
        return vuelto;
    }

    public void setVuelto(Moneda vuelto) {
        this.vuelto = vuelto;
    }

    public Moneda getTotal() {
        return total;
    }

    public void setTotal(Moneda total) {
        this.total = total;
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
}
