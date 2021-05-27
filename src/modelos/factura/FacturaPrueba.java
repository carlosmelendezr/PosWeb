package modelos.factura;


import java.util.Arrays;
import java.util.List;

public class FacturaPrueba {

    public static void main(String args[]) {
        Double tasaDolarHoy = 3256225.23;
        Double tasaPetroHoy = 0.000000003600;
        List<String> barras = Arrays.asList("759355622235");

        Producto p = new Producto(1,
                "PRODUCTO DE PRUEBA",
                "001",10.00,16.0,barras);

        List<Direccion> dir1 = Arrays.asList(new Direccion("AV. FRANCISCO LAZO MARTI"));
        List<Telefono> tel1 = Arrays.asList(new Telefono(212,6616263));

        Cliente c = new Cliente(1,"CLIENTE DE PRUEBA",309269577,"J",dir1,tel1);

        DatosFiscales fis = new DatosFiscales(65265,"TTB001195");

        Moneda Dolar = new Moneda(1,"USD","DOLAR","$",1.0,MonedaUtil.formatoUsd);
        Dolar.setEsMonedaBase(true);
        Moneda Bolivar = new Moneda(2,"VES","BOLIVAR","Bs.",tasaDolarHoy, MonedaUtil.formatoBs);
        Moneda Petro = new Moneda(3,"PTR","PETRO","Pt.",tasaPetroHoy,MonedaUtil.formatoPetro);

        Factura f = new Factura(Dolar);
        if (f.getActiva()) {
            f.asignarCliente(c);
            LineaFactura ln = new LineaFactura(1, p, 1.0);
            f.agregarLinea(ln);

            //Pago Efe1 = new Pago(Dolar, 5.0, 0.0);
            Pago Efe2 = new Pago(Bolivar, 5.0 * tasaDolarHoy, 0.0);
            //Pago Efe3  = new Pago(Bolivar, 100000.0, 0.0);

            //f.agregarPago(Efe1);

            f.agregarPago(Efe2);
            //f.agregarPago(Efe3);

            f.getTotales().imprimirTotal();
            FacturaTotal totBolivar = f.getTotales().Covertir(Bolivar);
            totBolivar.imprimirTotal();

            FacturaTotal totPetro = totBolivar.Covertir(Petro);
            totPetro.imprimirTotal();



        } else {
            System.out.println("Error :" + f.getMensaje());
        }



    }
}
