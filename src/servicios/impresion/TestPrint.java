package servicios.impresion;

import modelos.factura.Factura;
import modelos.factura.LineaFactura;
import modelos.factura.TipoMoneda;

import java.util.ArrayList;
import java.util.List;

public class TestPrint {


    public static void main(String args[]) {

    }

    public static void imprimir(Factura fac, TipoMoneda mon) {

        ImpBixolonSRP812 Bixolon = new ImpBixolonSRP812();

        Bixolon.inicializar("COM99", mon);
        Bixolon.cargarTablaComandos();

        Bixolon.agregarCliente(fac.getCliente());
        for (LineaFactura lin:fac.getLineas()) {
            Bixolon.agregarItem(lin);
        }

        Bixolon.Totalizar();
        Bixolon.enviarImpresora();
        Bixolon.finalizar();


    }
}
