package modelos.factura;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MonedaUtil {

    public static DecimalFormat formatoUsd = new DecimalFormat("###,###,###,###,###.##");
    public static DecimalFormat formatoBs = new DecimalFormat("###,###,###,###,###,###.##");
    public static DecimalFormat formatoPetro = new DecimalFormat("###.##########" );

    public static MonedaConversion dolarToBs = new MonedaConversion("USD", "VES","*");
    public static List<MonedaConversion> listaMonedaConversion;
    public static MathContext precision ;

    public static void inicializar() {
        listaMonedaConversion = new ArrayList<>();
        listaMonedaConversion.add(dolarToBs);
        formatoUsd.setMinimumFractionDigits(2);
        formatoBs.setMinimumFractionDigits(2);
    }



    public static Moneda ConvertirValor(TipoMoneda origen, TipoMoneda destino, Moneda valor) {

        Moneda resultante = new Moneda(valor) ;
        //resultante.setPrecision(new MathContext(2,MathContext.));
        String Operacion="*";

        for (MonedaConversion mon:listaMonedaConversion) {
            if (mon.getCodigoDestino().equals(origen.getCodmoneda())) {
                Operacion="/";
            }

        }
        if (Operacion.equals("*")) {
            resultante.multiplicar(destino.getTasacambio());
         } else {
            resultante.dividir(origen.getTasacambio());
         }

        System.out.println("Valor = "+valor.getValor());
        System.out.println("Tasa = "+origen.getTasacambio().getValor());
        System.out.println("Resul = "+resultante.getValor());
         return resultante;
    }






}
