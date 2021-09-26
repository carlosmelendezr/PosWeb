package servicios.impresion;

import modelos.factura.*;

import java.util.ArrayList;
import java.util.List;

public class TestPrint {


    public static void main(String args[]) {

        TipoMoneda  Bolivar = new TipoMoneda(2,"VES","BOLIVAR","Bs.",new Moneda(4.3), MonedaUtil.formatoBs);

        //ImpBixolonSRP812 Bixolon = new ImpBixolonSRP812();
        BixolonServicios svr = new BixolonServicios();
        if (svr.abrirPuerto("COM5")) {
            if (svr.CheckPrinter()) {
                svr.cerrarPuerto();
                System.out.println("Impresora OK");

            }
        }

        /*Bixolon.inicializar("COM5", Bolivar, 1);
        Bixolon.cargarTablaComandos();
        Bixolon.ReporteX();*/

    }

    public static void imprimir(Factura fac, TipoMoneda mon) {

        ImpBixolonSRP812 Bixolon = new ImpBixolonSRP812();

        /*Bixolon.inicializar("COM5", mon,1);
        Bixolon.cargarTablaComandos();
        Bixolon.ReporteX();*/

        /*Bixolon.agregarCliente(fac.getCliente());
        for (LineaFactura lin:fac.getLineas()) {
            Bixolon.agregarItem(lin);
        }

        Bixolon.Totalizar();
        Bixolon.enviarImpresora();
        Bixolon.finalizar();*/


    }
}
