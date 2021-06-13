package servicios.impresion;

import java.util.List;

public class Impresora {
    String marca;
    String modelo;
    int puerto;

    Trama inicioTrama;
    Trama finTrama;
    Trama estatus;
    Trama ACK;
    Trama NAK;
    Trama finBloque;
    Trama finTranmision;
    List<Estatus> listaEstatus;
    List<Comando> listaComandos;


    class Trama {
        String etiqueta;
        short comando;

        public Trama(String etiqueta, short valor) {
            this.etiqueta = etiqueta;
            this.comando = valor;
        }
    }

    class Estatus {
        short valor;
        String descripcion;

        public Estatus(short valor, String descripcion) {
            this.valor = valor;
            this.descripcion = descripcion;
        }
    }





}
