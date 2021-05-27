package modelos.factura;

import java.text.DecimalFormat;

public class MonedaUtil {

    public static DecimalFormat formatoUsd = new DecimalFormat("###,###,###,###,###.##");
    public static DecimalFormat formatoBs = new DecimalFormat("###,###,###,###,###,###.##");
    public static DecimalFormat formatoPetro = new DecimalFormat("###.##########" );


    public static Double Convertir(Moneda origen, Moneda destino) {
         Double resultante = 0.0;
         if (origen.getEsMonedaBase()) {
             resultante = origen.getValor() * destino.getTasacambio();
         } else {
             if (destino.getTasacambio()>0)
                resultante = origen.getValor() / destino.getTasacambio();
         }
         return resultante;
    }

    public static Double ConvertirValor(Moneda origen, Moneda destino, Double Valor) {
        Double resultante = 0.0;
        if (origen.getEsMonedaBase() && !destino.getEsMonedaBase()) {
            resultante = Valor * destino.getTasacambio();
        } else {
            if (destino.getTasacambio()>0)
                resultante = Valor / destino.getTasacambio();
        }
        return resultante;
    }





}
