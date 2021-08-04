package servicios.impresion;

import modelos.factura.*;

import java.util.ArrayList;
import java.util.List;

public interface ImpresoraFiscal {


    public void inicializar( String puerto, TipoMoneda mon);

    public void agregarItem(LineaFactura lin) ;

    public void cargarTablaComandos();

    public void agregarCliente(Cliente cli);

    public void Totalizar() ;

    public void enviarImpresora();

    public boolean enviarComando(String comm);

    public void cerrarPuerto();

    public boolean abrirPuerto();

    public void finalizar();


}
