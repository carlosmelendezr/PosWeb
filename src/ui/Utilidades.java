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

        String currentText = texto.replaceAll("\\.", "").replace(",", "");
        Moneda mon = new Moneda(0.0);

        try {
            Double valor = Double.parseDouble(currentText);
            mon.setValor(valor);
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return mon;
    }
}
