package ui;

import javafx.scene.control.TextField;
import modelos.factura.Moneda;
import modelos.factura.MonedaUtil;

import java.text.DecimalFormat;

public class Utilidades {

    public static void entradaFormatoBs(TextField texto) {
        String currentText = texto.getText().replaceAll("\\.", "").replace(",", "");

        System.out.println("texto:"+currentText);
        if (!currentText.isEmpty()) {
            long longVal = Long.parseLong(currentText.concat(currentText));
            texto.setText(MonedaUtil.formatoBs.format(longVal));
        }

    }

    public static Moneda textoToMoneda(String texto) {
        Moneda mon = new Moneda(0.0);
        if (texto.isEmpty()) {
            return mon;
        }

        try {
            Double valor = Double.parseDouble(texto);
            mon.setValor(valor);
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return mon;
    }
}
