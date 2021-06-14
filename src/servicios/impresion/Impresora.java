package servicios.impresion;

import java.util.List;

public class Impresora {
    String marca;
    String modelo;
    String puerto;

    List<EstatusImpresora> listaEstatus;
    List<Comando> tablaComandos;
    List<Comando> listaComandos;

    public void cargarTablaComandos() {

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
