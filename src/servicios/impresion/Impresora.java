package servicios.impresion;

import modelos.factura.Cliente;
import modelos.factura.Direccion;

import java.util.List;

public class Impresora {
    String marca;
    String modelo;
    String puerto;

    String rif;
    String cedula;
    String razonsocial;
    List<String> direccion;

    List<EstatusImpresora> listaEstatus;
    List<Comando> tablaComandos;
    List<Comando> listaComandos;

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

    public void agregarComando(String nombre, List<Param> param) {
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
