package modelos.factura;

public class DatosFiscales {
    private Integer facturaFiscal;
    private String  serialImpresora;

    public DatosFiscales(Integer facturaFiscal, String serialImpresora) {
        this.facturaFiscal = facturaFiscal;
        this.serialImpresora = serialImpresora;
    }

    public Integer getFacturaFiscal() {
        return facturaFiscal;
    }

    public void setFacturaFiscal(Integer facturaFiscal) {
        this.facturaFiscal = facturaFiscal;
    }

    public String getSerialImpresora() {
        return serialImpresora;
    }

    public void setSerialImpresora(String serialImpresora) {
        this.serialImpresora = serialImpresora;
    }
}
