package servicios.impresion;

import modelos.factura.Moneda;

public class Util {

    public static String llenarCeros(Moneda monto,int longitud) {
        String sMonto = String.format("%,.2f",monto.getValor());
        sMonto = sMonto.replace(".", "");
        sMonto = sMonto.replace(",", "");
        sMonto = llenarIzq(sMonto,longitud,"0");
        return sMonto;
    }

    public static String llenarCeros(Double valor,int longitud) {
        String sMonto = String.format("%,.2f",valor);
        sMonto = sMonto.replace(".", "");
        sMonto = sMonto.replace(",", "");
        sMonto = llenarIzq(sMonto,longitud,"0");
        return sMonto;
    }

    public static String llenarDerecha(String original, int longitud, String Caracter) {
        StringBuilder sb = new StringBuilder();
        sb.append(original);

        for (int i = original.length(); i < longitud; i++) {
            sb.append(Caracter);
        }
        return sb.toString();
        //return sb.substring(inputString.length()) + inputString;
    }

    public static String llenarIzq(String original, int longitud, String Caracter) {
        StringBuilder sb = new StringBuilder();
        int falta = longitud-original.length();
        for (int i = 0; i < falta; i++) {
            sb.append(Caracter);
        }

        sb.append(original);
        return sb.toString();
        //return sb.substring(inputString.length()) + inputString;
    }
}
