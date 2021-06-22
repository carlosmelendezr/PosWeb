package servicios.impresion;

import modelos.factura.Factura;

import java.util.ArrayList;
import java.util.List;

public class TestPrint {


    public static void main(String args[]) {





    }

    public static void imprimir(Factura fac) {

        Impresora Bixolon = new Impresora("Bixolon","SRP-812","COM1");
        Bixolon.cargarTablaComandos();

        Comando AbrirGaveta = new Comando("Abrir Gaveta","y");
        Comando AgregarProducto = new Comando("producto"," ");



        /*List<Param> pCli = new ArrayList();
        Param pRif = new Param("rif", "V12345678-2" );
        pCli.add(pRif);
        Bixolon.agregarFuncion("cliente.identificacion",pCli);*/


        Bixolon.agregarItem(fac.getLineas().get(0));



    }
}
