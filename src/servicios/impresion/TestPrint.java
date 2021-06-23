package servicios.impresion;

import modelos.factura.Factura;
import modelos.factura.TipoMoneda;

import java.util.ArrayList;
import java.util.List;

public class TestPrint {


    public static void main(String args[]) {





    }

    public static void imprimir(Factura fac, TipoMoneda mon) {

        ImpBixolonSRP812 Bixolon = new ImpBixolonSRP812();

        Bixolon.inicializar("COM1", mon);
        Bixolon.cargarTablaComandos();

        Bixolon.agregarCliente(fac.getCliente());
        Bixolon.agregarItem(fac.getLineas().get(0));
        Bixolon.Totalizar();
        Bixolon.enviarImpresora();


    }
}
