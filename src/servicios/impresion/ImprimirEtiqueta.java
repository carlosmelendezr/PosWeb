package servicios.impresion;


import modelos.factura.Producto;
import zpl.utils.ZebraUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImprimirEtiqueta {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    StringBuilder etiq = new StringBuilder();
  
    
     
    public void agregarComandoEPL(String comm) {
        etiq.append(comm+"\n");
    }

    public void Precio(Producto p) {

        agregarComandoEPL("N");
        agregarComandoEPL( "q456" );
        agregarComandoEPL( "Q248,2+0");
        agregarComandoEPL( "S2");
        agregarComandoEPL( "D8");
        agregarComandoEPL( "ZT");
        agregarComandoEPL( "TTh:m");
        agregarComandoEPL( "TDy2.mn.dd");
        agregarComandoEPL( "A30,05,0,2,1,1,N,\""+p.getDescripcion()+'"');
        agregarComandoEPL( "B57,25,0,E80,3,3,102,B,\""+p.getCodigo()+'"');
        agregarComandoEPL( "A30,156,4,4,1,1,N,REF: \""+p.getPrecioFormato()+'"');
        agregarComandoEPL( "A61,191,0,4,1,1,N,Cod:\"");
        agregarComandoEPL( "A144,193,0,4,1,1,N,\""+p.getReferencia()+'"');

        agregarComandoEPL( "A61,205,0,4,1,1,N,Fecha :\"");
        agregarComandoEPL( "A144,205,0,4,1,1,N,\""+df.format(new Date())+'"');
        agregarComandoEPL( "P1" );

        try {
            ZebraUtils.printZpl(etiq.toString(), "ARKSCAN 2054A");


        } catch (Exception e) {

        e.printStackTrace();

        }



    }

}
