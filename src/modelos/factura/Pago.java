package modelos.factura;

import ui.Contexto;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Pago {
    private Integer id;
    private TipoMoneda tipoMoneda;
    private Moneda monto;
    private Moneda montoMonedaBase;
    private String referencia;
    private Banco  banco;
    private Moneda vuelto;
    private Moneda total;
    private String montoformato;
    private String montoformatobase;
    private Calendar fechapago;
    private Calendar   fechareg;
    private String destipoMoneda;

    public Pago(TipoMoneda tipoMoneda, Moneda monto, Moneda vuelto) {
        this.tipoMoneda = tipoMoneda;
        this.monto = monto;
        this.vuelto = vuelto;
        this.total =  monto;
        this.fechapago = Calendar.getInstance();
        this.fechareg = Calendar.getInstance();
        this.banco = new Banco();
        total.restar(vuelto);

    }

    public Moneda getMontoMonedaBase() {
        montoMonedaBase =  MonedaUtil.ConvertirValor(this.tipoMoneda, Contexto.Dolar,this.monto);
        return montoMonedaBase;
    }

    public String getMontoformatobase() {
        return MonedaUtil.formatoBs.format(getMontoMonedaBase().getValor());
    }

    public String getDestipoMoneda() {
        return tipoMoneda.getDescripcion();
    }

    public String getMontoformato() {
        return MonedaUtil.formatoBs.format(monto.getValor());
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

    public Calendar getFechapago() {
        return fechapago;
    }

    public void setFechapago(Calendar fechapago) {
        this.fechapago = fechapago;
    }

    public Calendar getFechareg() {
        return fechareg;
    }

    public void setFechareg(Calendar fechareg) {
        this.fechareg = fechareg;
    }
}
