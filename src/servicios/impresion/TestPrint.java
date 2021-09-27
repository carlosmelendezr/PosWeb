package servicios.impresion;

import modelos.datos.Operaciones;
import modelos.factura.*;

import java.util.ArrayList;
import java.util.List;

public class TestPrint {


    public static void main(String args[]) {

        TipoMoneda  Bolivar = new TipoMoneda(2,"VES","BOLIVAR","Bs.",new Moneda(4.3), MonedaUtil.formatoBs);

        Producto p = Operaciones.buscarProductoCodigo("7450008009834");
        ImprimirEtiqueta etiq = new ImprimirEtiqueta();
        etiq.Precio(p);
        etiq.Barra(p);

        //ImpBixolonSRP812 Bixolon = new ImpBixolonSRP812();
        /*BixolonServicios svr = new BixolonServicios();
        if (svr.abrirPuerto("COM5")) {
            if (svr.CheckPrinter()) {
                String Serial = svr.obtenerSerial();
                int ultimo = svr.ultimoNumero();
                svr.cerrarPuerto();
                System.out.println("Impresora OK - Serial "+Serial+" Ultima Factura "+ultimo);

            }
        }*/

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
