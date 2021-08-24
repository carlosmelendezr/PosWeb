package modelos.factura;

public class Telefono {
    private Integer codArea;
    private Integer numero;


    public Telefono(String codArea, String numero) {
        this.codArea = Integer.parseInt(codArea);
        this.numero = Integer.parseInt(numero);
    }


    public Integer getCodArea() {
        return codArea;
    }

    public void setCodArea(Integer codArea) {
        this.codArea = codArea;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "(" +codArea +") "+numero;

    }
}
