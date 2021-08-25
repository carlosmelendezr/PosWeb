package ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class soloNumero
        implements ChangeListener<String> {

    private TextField cantidad;

    public soloNumero(TextField cantidad) {
        this.cantidad = cantidad;
    }


    @Override
    public void changed(ObservableValue <? extends String> observable,
                        String oldValue,String newValue) {
        if (!newValue.matches("\\d{0,99}([\\.]\\d{0,99})?")) {
            this.cantidad.setText(oldValue);
        }
    }

}
