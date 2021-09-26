package servicios.impresion;

public class DatosImpresora {

    private Integer id;
    private String Puerto;
    private String Marca;
    private String Modelo;
    private String Serial;
    private Integer MaximoDigitosPrecio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPuerto() {
        return Puerto;
    }

    public void setPuerto(String puerto) {
        Puerto = puerto;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String serial) {
        Serial = serial;
    }

    public Integer getMaximoDigitosPrecio() {
        return MaximoDigitosPrecio;
    }

    public void setMaximoDigitosPrecio(Integer maximoDigitosPrecio) {
        MaximoDigitosPrecio = maximoDigitosPrecio;
    }
}
