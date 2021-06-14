package servicios.impresion;

import java.util.ArrayList;
import java.util.List;

public class TestPrint {


    public static void main(String args[]) {
        Impresora Bixolon = new Impresora();
        Bixolon.cargarTablaComandos();

        Comando AbrirGaveta = new Comando("Abrir Gaveta","y");
        Comando AgregarProducto = new Comando("producto"," ");



        Param p1 = new Param("precio", "163265225.22" );
        Param p2 = new Param("cantidad", "22"  );
        Param p3 = new Param("descripcion", "Producto de Prueba" );
        List<Param> pList = new ArrayList();
        pList.add(p1);
        pList.add(p2);
        pList.add(p3);

        Bixolon.agregarComando("producto",pList);




    }
}
