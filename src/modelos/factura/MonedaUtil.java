package modelos.factura;

import java.text.DecimalFormat;

public class MonedaUtil {

    public static DecimalFormat formatoUsd = new DecimalFormat("###,###,###,###,###.##");
    public static DecimalFormat formatoBs = new DecimalFormat("###,###,###,###,###,###.##");
    public static DecimalFormat formatoPetro = new DecimalFormat("###.##########" );


    public static Double Convertir(Moneda origen, Moneda destino) {
         Double resultante;
         resultante = origen.getValor() * destino.getTasacambio();
         return resultante;
    }

}
