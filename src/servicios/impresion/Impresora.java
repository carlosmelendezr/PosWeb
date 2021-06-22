package servicios.impresion;

import modelos.factura.Cliente;
import modelos.factura.Direccion;
import modelos.factura.LineaFactura;
import modelos.factura.Moneda;

import java.util.ArrayList;
import java.util.List;

public class Impresora {
    String marca;
    String modelo;
    String puerto;

    String rif;
    String cedula;
    String razonsocial;
    List<String> direccion;
    List<LineaItemBixolon> items;

    List<EstatusImpresora> listaEstatus;
    List<Comando> tablaComandos;
    List<Comando> listaComandos;
    List<TasaImpresora> tasas;

    public Impresora(String marca, String modelo, String puerto) {
        this.marca = marca;
        this.modelo = modelo;
        this.puerto = puerto;
        this.tasas = new ArrayList<TasaImpresora>();

        TasaImpresora Excento = new TasaImpresora(new Moneda(0)," ");
        TasaImpresora Tasa1   = new TasaImpresora(new Moneda(16),"!");
        TasaImpresora Tasa2   = new TasaImpresora(new Moneda(8),"%");

        tasas.add(Excento);
        tasas.add(Tasa1);
        tasas.add(Tasa2);

    }

    public void agregarItem(LineaFactura lin) {
        LineaItemBixolon item = new LineaItemBixolon();

        item.setCodigo(lin.getReferencia());
        item.setCantidad(lin.getCantidad());
        item.setPrecio(lin.getPrecio());
        item.setDescripcion(lin.getProducto().getDescripcion());
        item.setTasa(lin.getProducto().getAlicuota());

    }


    public void cargarTablaComandos() {

    }

    public void agregarCliente(Cliente cli) {
        this.rif = cli.getTiporif()+cli.getRif();
        this.cedula = rif;
        this.razonsocial = cli.getRazonsocial();
        for(Direccion dir:cli.getDirecciones()) {
            direccion.add(dir.getTexto());
        }

    }

    public void agregarFuncion (String nombre, List<Param> param) {
        Comando comm = buscarComando(nombre);
        comm.setParam(param);
        this.listaComandos.add(comm);
    }



    public Comando buscarComando(String nombre) {
        Comando comm = listaComandos.stream().filter(comando -> nombre.equals(comando.getNombre())).findAny()
                .orElse(null);
        return comm;
    }






}
