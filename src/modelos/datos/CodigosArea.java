package modelos.datos;

import java.util.Arrays;
import java.util.List;

public class CodigosArea {
    private List<String> celular = Arrays.asList("0424","0414","0412","0416","0426");
    private List<String> local = Arrays.asList("0212");

    public  List ListarCelular() {
        return celular;
    }

    public  List ListarLocal() {
        return local;
    }


}
