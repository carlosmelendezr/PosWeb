package modelos.factura;

import java.util.Date;

public class Pago {
    private Integer id;
    private Moneda  moneda;
    private Double  monto;
    private String  referencia;
    private Banco   banco;
    private Double  vuelto;
    private Double  total;
    private Date    fechapago;
    private Date    fechareg;

    public Pago(Moneda moneda, Double monto, Double vuelto) {
        this.moneda = moneda;
        this.monto = monto;
        this.vuelto = vuelto;
        this.total = monto - vuelto;

        this.moneda.setValor(total);
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

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
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

    public Double getVuelto() {
        return vuelto;
    }

    public void setVuelto(Double vuelto) {
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

    public Double getTotal() {
        return total;
    }
}
