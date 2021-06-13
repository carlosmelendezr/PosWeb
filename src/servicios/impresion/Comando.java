package servicios.impresion;

import java.util.List;

public class Comando {
    String previo;
    String nombre;
    String valor;
    String valorHex;
    List<Param> param;


    public Comando(String nombre, String valor) {
        this.nombre = nombre;
        this.valor = valor;
        this.valorHex = Integer.toHexString(Integer.parseInt(valor));
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
}
