package servicios.impresion;

import modelos.factura.Moneda;

public class TasaImpresora {
    Moneda tasa;
    String comando;

    public TasaImpresora(Moneda tasa, String comando) {
        this.tasa = tasa;
        this.comando = comando;
    }

    public Moneda getTasa() {
        return tasa;
    }

    public void setTasa(Moneda tasa) {
        this.tasa = tasa;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }
}
