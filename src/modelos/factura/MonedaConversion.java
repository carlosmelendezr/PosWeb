package modelos.factura;

public class MonedaConversion {
    private String codigoOrigen;
    private String codigoDestino;
    private String operacion;

    public MonedaConversion(String codigoOrigen, String codigoDestino, String operacion) {
        this.codigoOrigen = codigoOrigen;
        this.codigoDestino = codigoDestino;
        this.operacion = operacion;
    }

    public String getCodigoOrigen() {
        return codigoOrigen;
    }

    public void setCodigoOrigen(String codigoOrigen) {
        this.codigoOrigen = codigoOrigen;
    }

    public String getCodigoDestino() {
        return codigoDestino;
    }

    public void setCodigoDestino(String codigoDestino) {
        this.codigoDestino = codigoDestino;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
}
