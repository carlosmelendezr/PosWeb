package servicios.impresion;

import java.util.List;

public class Comando {
    String previo;
    String nombre;
    String valor;
    List<Param> param;




    public Comando(String nombre, String valor) {
        this.previo = "";
        this.nombre = nombre;
        this.valor = valor;
    }

    public Comando(String previo, String nombre, String valor) {
        this.previo = previo;
        this.nombre = nombre;
        this.valor = valor;
    }

    public List<Param> getParam() {
        return param;
    }

    public void setParam(List<Param> param) {
        this.param = param;
    }

    public String getPrevio() {
        return previo;
    }

    public void setPrevio(String previo) {
        this.previo = previo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
