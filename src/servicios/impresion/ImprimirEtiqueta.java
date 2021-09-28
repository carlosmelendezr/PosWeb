package servicios.impresion;


import modelos.factura.Moneda;
import modelos.factura.MonedaUtil;
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
        agregarComandoEPL( "A20,05,0,3,1,1,N,\""+p.getDescripcion()+'"');
        agregarComandoEPL( "A20,30,0,4,1,1,N,\""+p.getCodigo()+'"');

        Moneda precioIVA = p.getPrecio();
        precioIVA.sumarIVA(new Moneda(16));

        String precioFormato = MonedaUtil.formatoUsd.format( precioIVA.getValor());

        agregarComandoEPL( "A20,110,0,4,2,2,N,\"REF "+precioFormato+'"');
        agregarComandoEPL( "A20,55,0,4,1,1,N,\""+p.getReferencia()+'"');

        agregarComandoEPL( "A61,205,0,4,1,1,N,Fecha :\"");
        agregarComandoEPL( "A144,205,0,4,1,1,N,\""+df.format(new Date())+'"');
        agregarComandoEPL( "P1" );

        try {
            ZebraUtils.printZpl(etiq.toString(), "ELTRON");


        } catch (Exception e) {

        e.printStackTrace();

        }



    }

    public void Barra(Producto p) {

        agregarComandoEPL("N");
        agregarComandoEPL( "q456" );
        agregarComandoEPL( "Q248,2+0");
        agregarComandoEPL( "S2");
        agregarComandoEPL( "D8");
        agregarComandoEPL( "ZT");
        agregarComandoEPL( "TTh:m");
        agregarComandoEPL( "TDy2.mn.dd");
        agregarComandoEPL( "A20,05,0,3,1,1,N,\""+p.getDescripcion()+'"');
        agregarComandoEPL( "B57,40,0,E30,3,3,102,B,\""+p.getCodigo()+'"');
        agregarComandoEPL( "A144,183,0,4,1,1,N,\""+p.getReferencia()+'"');
        agregarComandoEPL( "P1" );

        try {
            ZebraUtils.printZpl(etiq.toString(), "ELTRON");


        } catch (Exception e) {

            e.printStackTrace();

        }



    }

}
