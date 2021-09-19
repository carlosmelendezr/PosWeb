package servicios.impresion;

import modelos.factura.*;

import java.util.ArrayList;
import java.util.List;
import tfhka.*;
import tfhka.ve.*;




public class ImpBixolonSRP812
        implements ImpresoraFiscal {
    String marca="Bixolon";
    String modelo="SRP-812";
    String puerto;
    TipoMoneda moneda;

    private tfhka.ve.ReportData Reporte;
    private tfhka.ve.ReportData[] ReporteArray;
    private PrinterStatus StatusError;
    private S1PrinterData EstatusS1;
    private S2PrinterData EstatusS2;
    private S3PrinterData EstatusS3;
    private S4PrinterData EstatusS4;
    private S5PrinterData EstatusS5;
    private S6PrinterData EstatusS6;
    private S7PrinterData EstatusS7;
    private S8EPrinterData EstatusS8E;
    private S8PPrinterData EstatusS8P;
    private String Output = "";
    private SVPrinterData EstatusSV;
    public boolean Respuesta;

    private Tfhka Impresora;


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

        Impresora = new tfhka.ve.Tfhka();

        abrirPuerto();

    }

    public boolean abrirPuerto() {
        System.out.println("Abriendo Puerto " + this.puerto);
        try {
            Respuesta = Impresora.OpenFpctrl(this.puerto);
            if (Respuesta) {
                System.out.println("Puerto " + this.puerto + " abierto.");

            } else {
                System.out.println("Error de Puerto " + this.puerto);

            }
        } catch (Exception e) {
            System.out.println("Error al abrir puerto "+e.getMessage());
            cerrarPuerto();
        }
        return Respuesta;
    }

    public boolean enviarComando(String comm) {
        boolean exito =false;
        try {
            Impresora.SendCmd(comm);
            exito = true;
        } catch (PrinterException Excepcion) {
            System.out.println("Error :"+Excepcion.toString());

        }
        return exito;
    }

    public void cerrarPuerto() {
        Impresora.CloseFpctrl();
    }

    public void finalizar() {
        cerrarPuerto();
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
        if (cli==null) {return;}

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
            enviarComando(comm);
        }
        System.out.println("* * *  FIN DE IMPRESION * * * ");
    }

    public void ReporteZ() {
        String Comando = "I0Z";
        listaComandos.add(Comando);
        finalizar();
    }

    public void ReporteX() {
        String Comando = "I0X";
        listaComandos.add(Comando);
        finalizar();
    }





}
