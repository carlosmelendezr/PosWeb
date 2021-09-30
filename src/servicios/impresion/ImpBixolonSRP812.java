package servicios.impresion;

import modelos.datos.Constantes;
import modelos.factura.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import tfhka.*;
import tfhka.ve.*;




public class ImpBixolonSRP812
        implements ImpresoraFiscal {

    //public static Integer maximo_Digitos_Precio  = 10;
    public static Integer maximo_Digitos_Cantidad_Enteros = 14;
    public static Integer maximo_Digitos_Cantidad_Decimales = 3;

    private String marca="Bixolon";
    private String modelo="SRP-812";
    private String puerto;
    private Boolean puertoAbierto;
    private TipoMoneda moneda;
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
    private boolean Respuesta;
    private Tfhka Impresora;
    private File archivo;
    private FileOutputStream log;
    private Integer numeroFactura;
    private DatosImpresora datImp;
    public static Integer MaximoDigitosPrecio;

    List<EstatusImpresora> listaEstatus;
    List<Comando> tablaComandos;
    List<String> listaComandos;
    List<TasaImpresora> tasas;

    public void inicializar( DatosImpresora datImp, TipoMoneda mon, Integer numeroFac) {
        this.datImp = datImp;
        this.puerto = datImp.getPuerto();
        this.moneda = mon;
        this.tasas = new ArrayList<TasaImpresora>();
        this.numeroFactura = numeroFac;

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
        puertoAbierto = false;
        abrirPuerto();
        datosInternos();

    }

    private void guardarLog() {
        archivo = new File(Constantes.dirOut+"factura_"+this.numeroFactura+".txt");

        try {
            log = new FileOutputStream(archivo);
        } catch (Exception e) {
            System.out.println("Error al crear el archivo "+Constantes.dirOut+"factura.txt");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(log));
        try {

            for(String comm:listaComandos) {
                bw.write(comm);
                bw.newLine();
            }
            bw.close();
            log.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al escribir el archivo "+Constantes.dirOut+"factura.txt");
        }

    }

    public boolean abrirPuerto() {

        System.out.println("Abriendo Puerto " + this.puerto);
        try {
            Respuesta = Impresora.OpenFpctrl(this.puerto);
            if (Respuesta) {
                System.out.println("Puerto " + this.puerto + " abierto.");
                puertoAbierto = true;

            } else {
                System.out.println("Error de Puerto " + this.puerto);
                puertoAbierto = false;
            }
        } catch (Exception e) {
            System.out.println("Error al abrir puerto "+e.getMessage());
            cerrarPuerto();
        }
        return Respuesta;
    }

    public boolean enviarComando(String comm) {
        boolean exito =false;
        if (Impresora == null || comm==null) {
            System.out.println("Puerto cerrado, no se envia comando.");
            return false ;
        }

        try {
            System.out.println("Comando "+comm);
            Impresora.SendCmd(comm);
            exito = true;
        } catch (PrinterException Excepcion) {
            System.out.println("Error de Impresion:");
        }
        guardarLog();
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
        item.setMaxLongPrecio(datImp.getMaximoDigitosPrecio());

        Moneda precioLocal =  new Moneda(lin.getPreciobase());
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

    public void datosInternos() {
        String Comando = "@DOC #:"+Util.llenarCeros(this.numeroFactura,10);
        listaComandos.add(Comando);

    }

    public void agregarCliente(Cliente cli) {
        if (cli==null) {return;}

        if (cli.getRif()==null) {return;}

        String Comando = new String();
        Comando = "iR*"+cli.getTiporif()+cli.getRif();
        listaComandos.add(Comando);

        Comando = "iS*"+cli.getRazonsocial();;
        listaComandos.add(Comando);

        Integer lin=0;
        if (cli.getDirecciones()!=null) {
            for (Direccion dir : cli.getDirecciones()) {
                Comando = "i" + Util.llenarIzq(lin.toString(), 2, "0") + dir.getTexto();
                listaComandos.add(Comando);
                lin++;
            }
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
        enviarComando("I0Z");
        finalizar();
    }

    public void ReporteX() {
        enviarComando("I0X");
        finalizar();
    }





}
