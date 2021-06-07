package modelos.datos;

public class Constantes {
    public static String dirLocal = "C:/posweb/db/";


    /* Creacion de tablas */
    public static String Factura = "CREATE TABLE IF NOT EXISTS factura ("
            + "	id integer PRIMARY KEY,"
            + " numero integer,"
            + "	caja integer ,"
            + "	idfiscal integer NOT NULL,"
            + "	idcliente integer, "
            + "	idpago integer, "
            + "	total real, "
            + "	impuesto real, "
            + "	base real, "
            + "	descuento real, "
            + " imprime integer, "
            + "	activa integer, "
            + "	pagada integer, "
            + "	cancelada integer, "
            + "	error integer, "
            + "	espera integer "
            + ");";

}
