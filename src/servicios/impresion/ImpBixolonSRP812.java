package servicios.impresion;

import modelos.factura.*;

import java.util.ArrayList;
import java.util.List;

public class ImpBixolonSRP812
        implements ImpresoraFiscal {
    String marca="Bixolon";
    String modelo="SRP-812";
    String puerto;
    TipoMoneda moneda;

    List<EstatusImpresora> listaEstatus;
    List<Comando> tablaComandos;
    List<String> listaComandos;
    List<TasaImpresora> tasas;

    public void inicializar( String puerto, TipoMoneda mon) {
        this.marca = marca;
        this.modelo = modelo;
        this.puerto = puerto;
        this.moneda = mon;
        this.tasas = new ArrayList<TasaImpresora>();

        TasaImpresora Excento = new TasaImpresora(new Moneda(0)," ");
        TasaImpresora Tasa1   = new TasaImpresora(new Moneda(16),"!");
        TasaImpresora Tasa2   = new TasaImpresora(new Moneda(8),"\"");
        TasaImpresora Tasa3   = new TasaImpresora(new Moneda(8),"#");

        tasas.add(Excento);
        tasas.add(Tasa1);
        tasas.add(Tasa2);
        tasas.add(Tasa3);
        this.listaComandos = new ArrayList<>();

    }

    public void agregarItem(LineaFactura lin) {
        LineaItemBixolon item = new LineaItemBixolon(tasas);

        Moneda precioLocal =  new Moneda(lin.getPrecio());
        precioLocal.multiplicar(moneda.getTasacambio());

        item.setCodigo(lin.getReferencia());
        item.setCantidad(lin.getCantidad());
        item.setPrecio(precioLocal);
        item.setDescripcion(lin.getProducto().getDescripcion());
        item.setTasa(lin.getProducto().getAlicuota());
        this.listaComandos.add(  item.armarComando() ) ;

    }


    public void cargarTablaComandos() {

    }

    public void agregarCliente(Cliente cli) {
        String Comando = new String();
        Comando = "iR*"+cli.getTiporif()+cli.getRif();
        listaComandos.add(Comando);

        Comando = "iS*"+cli.getRazonsocial();;
        listaComandos.add(Comando);

        Integer lin=0;
        for(Direccion dir:cli.getDirecciones()) {
            Comando = "i"+Util.llenarIzq(lin.toString(),2,"0")+dir.getTexto();
            listaComandos.add(Comando);
            lin++;
        }
    }

    public void Totalizar() {
        String Comando = "101";
        listaComandos.add(Comando);
    }

    public void enviarImpresora() {
        System.out.println("* * * INICIO DE IMPRESION * * * ");
        for(String comm:listaComandos) {
            System.out.println(comm);
        }
        System.out.println("* * *  FIN DE IMPRESION * * * ");
    }





}
