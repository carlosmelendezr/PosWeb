package modelos.datos;

import java.util.ArrayList;
import java.util.List;

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
            + "	fecsync date, "
            + "	tasacambio real "
            + ");";

    public static String SQL_IND_FACTURA_NUM = "CREATE UNIQUE INDEX IF NOT EXISTS ifacnum ON factura (numero)";
    public static String SQL_IND_FACTURA_CLI = "CREATE INDEX IF NOT EXISTS ifaccli ON factura (idcliente)";
    public static String SQL_IND_FACTURA_FEC = "CREATE INDEX IF NOT EXISTS ifacfec ON factura (fecha)";

    public static String SQL_CONSECUTIVOS = "CREATE TABLE IF NOT EXISTS tabla_consec (id integer PRIMARY KEY, " +
            "nombre text,numero integer)";

    public static String SQL_INSERTAR_CONSEC_INICIAL = "INSERT INTO tabla_consec (nombre,numero) " +
            "VALUES ('FACTURA',0),('DEVOLUCION',0) ";

    public static String SQL_INCREMENTA_FACTURA = "UPDATE tabla_consec SET numero=numero+1 WHERE nombre='FACTURA' ";

    public static String SQL_ULTIMA_FACTURA = "SELECT numero FROM tabla_consec  WHERE nombre='FACTURA' LIMIT 1 ";

    public static String SQL_FACTURAS_ACTIVA = "SELECT * FROM factura  WHERE activa=1 LIMIT 1 ";
    public static String SQL_FACTURAS_ESPERA = "SELECT * FROM factura  WHERE espera=1 ";



    public static List<String> crearConsecutivos() {
        List<String> queris = new ArrayList<>();
        queris.add(SQL_CONSECUTIVOS);
        queris.add(SQL_INSERTAR_CONSEC_INICIAL);
        return queris;
    }

    public static String SQL_INSERTAR_FACTURA = "INSERT INTO factura " +
            "(numero,idcliente,moneda,caja,total,impuesto,base,descuento," +
            "imprime,activa,pagada,cancelada,error,espera,fecha,hora, tasacambio) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static String SQL_CREAR_TASAIMP = "CREATE TABLE IF NOT EXISTS tabla_tasaimp (id integer PRIMARY KEY, " +
            "alicuota real)";

    public static String SQL_INSERTAR_TASA = "INSERT INTO tabla_tasaimp (alicuota) VALUES (?) ";

    public static String SQL_INSERTAR_TASA_INICIAL = "INSERT INTO tabla_tasaimp (alicuota) VALUES (0),(16) ";

    public static List<String> crearTablasTasaImpuesto() {
        List<String> queris = new ArrayList<>();
        queris.add(SQL_CREAR_TASAIMP);
        queris.add(SQL_INSERTAR_TASA_INICIAL);
        return queris;
    }

    public static String SQL_CREAR_LINEAFAC = "CREATE TABLE IF NOT EXISTS fac_articulos ("
            + "	id integer PRIMARY KEY,"
            + " idfactura integer,"
            + " idproducto integer,"
            + " referencia text,"
            + " codbarra text,"
            + "	cantidad real ,"
            + "	precio real ,"
            + "	alicuota real ,"
            + "	descuento real, "
            + "	estatus integer "
            + ")";

    public static String SQL_IND_LINFAC_IDFAC = "CREATE INDEX IF NOT EXISTS ilinfacid  ON fac_articulos (idfactura)";
    public static String SQL_IND_LINFAC_IDPRO = "CREATE INDEX IF NOT EXISTS ilinfacpr  ON fac_articulos (idproducto)";
    public static String SQL_IND_LINFAC_BARRA = "CREATE INDEX IF NOT EXISTS ilinfacbar ON fac_articulos (codbarra)";

    public static List<String> crearTablasFactura() {
        List<String> queris = new ArrayList<>();
        queris.add(SQL_CREAR_FACTURA);
        queris.add(SQL_IND_FACTURA_NUM);
        queris.add(SQL_IND_FACTURA_CLI);
        queris.add(SQL_IND_FACTURA_FEC);

        queris.add(SQL_CREAR_LINEAFAC);
        queris.add(SQL_IND_LINFAC_IDFAC);
        queris.add(SQL_IND_LINFAC_IDPRO);
        queris.add(SQL_IND_LINFAC_BARRA);

        queris.add(SQL_CREAR_PAGOS);
        queris.add(SQL_IND_PAGO_IDFAC);
        queris.add(SQL_IND_PAGO_IDBAN);
        return queris;

    }

    public static String SQL_INSERTAR_LINEA_FACTURA = "INSERT INTO fac_articulos " +
            "(idfactura,idproducto,referencia,codbarra,cantidad,precio,alicuota,descuento, estatus) VALUES " +
            "(  ?      ,    ?     ,     ?    ,   ?     ,   ?    ,  ?   ,    ?  ,    ?,    ?    ) ";

    public static String SQL_OBTENER_LINEA_FACTURA = "SELECT * from fac_articulos WHERE idfactura=? ORDER BY codbarra ";

    public static String SQL_OBTENER_LINEA_FACTURA_AGRUPADO = "SELECT " +
            "id," +
            "idfactura,idproducto," +
            "referencia,codbarra," +
            "SUM(cantidad) as cantidad," +
            "precio," +
            "alicuota,descuento,estatus " +
            "FROM fac_articulos " +
            "WHERE idfactura=?" +
            "GROUP by idproducto,precio";

    public static String SQL_ACTUALIZAR_FACTURA = "UPDATE factura SET total=?,impuesto=?,base=?,descuento=?,idcliente=?," +
            "imprime=?,activa=?,pagada=?,cancelada=?,error=?,espera=? " +
            "WHERE id=?";


    public static String SQL_BORRAR_PAGOS = "DELETE FROM fac_pagos WHERE idfactura=?";

    public static String SQL_CREAR_PAGOS = "CREATE TABLE IF NOT EXISTS fac_pagos ("
            + "id integer PRIMARY KEY,"
            + "idfactura integer, "
            + "moneda text, "
            + "monto real, "
            + "vuelto real, "
            + "total real, "
            + "referencia text, "
            + "idbanco integer, "
            + "fecpago date,"
            + "horpago text,"
            + "fecreg date,"
            + "horreg text,"
            + "tasacambio real)";

    public static String SQL_IND_PAGO_IDFAC = "CREATE INDEX IF NOT EXISTS ipagoidfac  ON fac_pagos (idfactura)";
    public static String SQL_IND_PAGO_IDBAN = "CREATE INDEX IF NOT EXISTS ipagoidban  ON fac_pagos (idbanco)";

    public static String SQL_INSERTAR_PAGO = "INSERT INTO fac_pagos " +
            "(idfactura,moneda,monto,vuelto,total,referencia,idbanco,fecpago,horpago,fecreg,horreg,tasacambio) VALUES " +
            "(    ?,      ?   ,  ?,     ?,    ?,      ?,        ?,      ?,      ?,      ?,     ?  ,    ?  )";

    public static String SQL_BUSCAR_PAGOS ="SELECT * FROM fac_pagos WHERE idfactura=";


    public static String FacturaDatosFiscales= "CREATE TABLE IF NOT EXISTS fac_fiscal ("
            + "	id integer PRIMARY KEY,"
            + " idfactura integer,"
            + " numero integer,"
            + "	serial text ,"
            + ");";

    public static String SQL_IND_DATFIS_IDFAC = "CREATE INDEX IF NOT EXISTS idatfisidfac  ON fac_fiscal (idfactura)";

    public static String FacturaCliente= "CREATE TABLE IF NOT EXISTS fac_cliente ("
            + "	id integer PRIMARY KEY,"
            + " idfactura integer,"
            + " idcliente integer,"
            + "	serial text ,"
            + ");";

    public static String SQL_CREAR_PRODUCTOS = "CREATE TABLE IF NOT EXISTS productos ("
            + "id integer PRIMARY KEY,"
            + "idtipoprod integer, "
            + "descrip text, "
            + "imagen text, "
            + "idtipoimp integer, "
            + "idcategoria integer, "
            + "idmarca integer, "
            + "unmedida text, "
            + "precio real DEFAULT 0,"
            + "costo real DEFAULT 0,"
            + "stock real DEFAULT 0,"
            + "ref text,"
            + "refprov text,"
            + "codigo text,"
            + "idprov integer)";

    public static String SQL_INSERTAR_PRODUC_BUSCAR = "INSERT INTO producbuscar" +
            "(id,descrip,ref,codigo) VALUES " +
            "(  ?      ,    ?     ,     ?    ,   ?  ) ";

    public static String SQL_IND_PRODUCTOS_REF = "CREATE UNIQUE INDEX  iprodref ON productos (ref)";
    public static String SQL_IND_PRODUCTOS_COD = "CREATE UNIQUE INDEX  iprodcod ON productos (codigo)";
    public static String SQL_IND_PRODUCTOS_DES = "CREATE INDEX iproddes ON productos (descrip)";

    public static String SQL_PRODUC_BUSCAR = "CREATE VIRTUAL TABLE producbuscar USING FTS5(id,descrip,ref,codigo)";

    public static List<String> crearTablasProductos() {
        List<String> queris = new ArrayList<>();
        queris.add(SQL_CREAR_PRODUCTOS);
        queris.add(SQL_IND_PRODUCTOS_REF);
        queris.add(SQL_IND_PRODUCTOS_COD);
        queris.add(SQL_IND_PRODUCTOS_DES);
        queris.add(SQL_PRODUC_BUSCAR);

        return queris;

    }

    public static String SQL_CREAR_CLIENTES = "CREATE TABLE IF NOT EXISTS clientes ("
            + "id integer PRIMARY KEY,"
            + "tipo text, "
            + "cedula integer, "
            + "rif integer, "
            + "razonsoc text, "
            + "dir1 text, "
            + "dir2 text, "
            + "dir3 text, "
            + "telcelular text, "
            + "tellocal text,"
            + "teloficina text,"
            + "correo text)";

    public static String SQL_INSERTAR_CLIENTE = "INSERT INTO clientes " +
            "(tipo,cedula,rif,razonsoc,dir1,dir2,dir3,telcelular," +
            "tellocal,teloficina,correo) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

    public static String SQL_IND_CLIENTES_RIF = "CREATE UNIQUE INDEX  ipclirif ON clientes (rif)";
    public static String SQL_IND_CLIENTES_CED = "CREATE UNIQUE INDEX  ipclicod ON clientes (cedula)";
    public static String SQL_IND_CLIENTES_DES = "CREATE INDEX iclinom ON clientes (razonsoc)";

    public static List<String> crearTablasClientes() {
        List<String> queris = new ArrayList<>();
        queris.add(SQL_CREAR_CLIENTES);
        queris.add(SQL_IND_CLIENTES_RIF);
        queris.add(SQL_IND_CLIENTES_CED);
        queris.add(SQL_IND_CLIENTES_DES);


        return queris;

    }

    /* Control de Stock */
    public static String SQL_CREAR_MOVINV =  "CREATE TABLE IF NOT EXISTS movinv ("
            + "id integer PRIMARY KEY,"
            + "idtipomov integer, "
            + "idproducto integer, "
            + "cantidad real, "
            + "fecha date) ";

    public static String SQL_CREAR_TIPO_MOVINV =  "CREATE TABLE IF NOT EXISTS tipomovinv ("
            + "id integer PRIMARY KEY,"
            + "operacion integer, "
            + "descripcion text )";

    public static String SQL_INSERTAR_TIPO_INICIAL = "INSERT INTO tipomovinv (id,operacion,descripcion) " +
            "VALUES (1,1,'INGRESO DE MERCANCIA'),(2,-1,'SALIDA DE MERCANCIA'),(3,-1,'VENTA') ";

    public static String SQL_INSERTAR_MOVINV = "INSERT INTO movinv (idtipomov,idproducto,cantidad,fecha) " +
            "VALUES (?,?,?,?)";

    public static String SQL_ACTUALIZAR_STOCK = "UPDATE productos SET stock=stock+(?) WHERE id=?";

    public static List<String> crearTablasMovInv() {
        List<String> queris = new ArrayList<>();
        queris.add(SQL_CREAR_MOVINV);
        queris.add(SQL_CREAR_TIPO_MOVINV);
        queris.add(SQL_INSERTAR_TIPO_INICIAL);
        return queris;
    }






}
