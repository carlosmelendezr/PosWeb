package modelos.factura;


import modelos.datos.Operaciones;
import servicios.impresion.TestPrint;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FacturaPrueba {

    public static void main(String args[]) {
        Moneda tasaDolarHoy = new Moneda("3256225.23");
        // Moneda tasaDolarHoy = new Moneda("65dd");
        Moneda tasaPetroHoy = new Moneda("0.000000003600");
        List<String> barras = Arrays.asList("759355622235");


        /* Inicializar lista de monedas */
        MonedaUtil.inicializar();


        Producto p1 = new Producto(1,
                "PRODUCTO DE PRUEBA 1",
                "001",new Moneda("8.95"),new Moneda("16"),barras);

        Producto p2 = new Producto(2,
                "PRODUCTO DE PRUEBA 2",
                "001",new Moneda("1.33"),new Moneda("16"),barras);

        List<Direccion> dir1 = new ArrayList<>();
        dir1.add( new Direccion("AV. FRANCISCO LAZO MARTI"));
        dir1.add( new Direccion("RES. MAYORAL PLAZA"));
        List<Telefono> tel1 = Arrays.asList(new Telefono(212,6616263));

        Cliente c = new Cliente(1,"CLIENTE DE PRUEBA",309269577,"J",dir1,tel1);

        DatosFiscales fis = new DatosFiscales(65265,"TTB001195");

        TipoMoneda Dolar = new TipoMoneda(1,"USD","DOLAR","$",new Moneda("1"),MonedaUtil.formatoUsd);
        Dolar.setEsMonedaBase(true);
        TipoMoneda Bolivar = new TipoMoneda(2,"VES","BOLIVAR","Bs.",tasaDolarHoy, MonedaUtil.formatoBs);
        TipoMoneda Petro = new TipoMoneda(3,"PTR","PETRO","Pt.",tasaPetroHoy,MonedaUtil.formatoPetro,9);


        Factura f = new Factura(Dolar);
        if (f.getActiva()) {
            f.asignarCliente(c);
            LineaFactura ln = new LineaFactura(1, p1, 2.0, "759355622235");
            f.agregarLinea(ln);

            LineaFactura ln2 = new LineaFactura(2, p2, 4.0, "759355656223");
            f.agregarLinea(ln2);


            Pago Efe1 = new Pago(Dolar,new Moneda(5.0), new Moneda("0.0"));
            Pago Efe2 = new Pago(Bolivar, new Moneda("16281126.2"), new Moneda("0.0"));
            Pago Efe3  = new Pago(Bolivar, new Moneda("2500000"), new Moneda("0.0"));
            Pago Efe4  = new Pago(Bolivar, new Moneda("2702666.75"), new Moneda("0.0"));

            f.agregarPago(Efe1);

            f.agregarPago(Efe2);
            f.agregarPago(Efe3);
            f.agregarPago(Efe4);

            f.getTotales().imprimirTotal();
            FacturaTotal totBolivar = f.getTotales().Covertir(Bolivar);
            totBolivar.imprimirTotal();

            /*FacturaTotal totPetro = totBolivar.Covertir(Petro);
            totPetro.imprimirTotal();*/
            Operaciones.CrearFactura(f);


        } else {
            System.out.println("Error :" + f.getMensaje());
        }

        TestPrint.imprimir(f, Bolivar);

    }
}
