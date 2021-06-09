package modelos.datos;

public class Constantes {
    public static String dirLocal    = "C:/posweb/db/";
    public static String dbPrincipal = "posweb.db";

    /* Creacion de tablas */
    public static String SQL_CREAR_FACTURA = "CREATE TABLE IF NOT EXISTS factura ("
            + "	id integer PRIMARY KEY,"
            + " numero integer,"
            + " idcliente integer,"
            + " moneda text,"
            + "	caja integer ,"
            + "	total real, "
            + "	impuesto real, "
            + "	base real, "
            + "	descuento real, "
            + " imprime integer, "
            + "	activa integer, "
            + "	pagada integer, "
            + "	cancelada integer, "
            + "	error integer, "
            + "	espera integer, "
            + "	fecha date, "
            + " hora text, "
            + "	docweb text, "
            + "	fecsync date "
            + ");";

    public static String SQL_INSERTAR_FACTURA = "INSERT INTO factura " +
            "(numero,idcliente,moneda,caja,total,impuesto,base,descuento," +
            "imprime,activa,pagada,cancelada,error,espera,fecha,hora) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static String SQL_CREAR_LINEAFAC = "CREATE TABLE IF NOT EXISTS fac_articulos ("
            + "	id integer PRIMARY KEY,"
            + " idfactura integer,"
            + " idproducto integer,"
            + " referencia text,"
            + " codbarra text,"
            + "	cantidad real ,"
            + "	precio real ,"
            + "	alicuota real ,"
            + "	descuento real "
            + "); CREATE INDEX IF NOT EXISTS idx_idfac ON fac_articulos (idfactura)";

    public static String SQL_INSERTAR_LINEA_FACTURA = "INSERT INTO fac_articulos " +
            "(idfactura,idproducto,referencia,codbarra,cantidad,precio,alicuota,descuento) VALUES " +
            "(  ?      ,    ?     ,     ?    ,   ?     ,   ?    ,  ?   ,    ?  ,    ?    ) ";

    public static String FacturaDatosFiscales= "CREATE TABLE IF NOT EXISTS fac_fiscal ("
            + "	id integer PRIMARY KEY,"
            + " idfactura integer,"
            + " numero integer,"
            + "	serial text ,"
            + ");";

    public static String FacturaCliente= "CREATE TABLE IF NOT EXISTS fac_cliente ("
            + "	id integer PRIMARY KEY,"
            + " idfactura integer,"
            + " idcliente integer,"
            + "	serial text ,"
            + ");";

}
