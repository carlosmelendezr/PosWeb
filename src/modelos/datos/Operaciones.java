package modelos.datos;

import modelos.factura.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static modelos.datos.Connect.connect;

public class Operaciones {

    public static void IncrementaConsecutivo(String SqlComm) {
        try {
            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(SqlComm);
            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Factura ResultToFactura(ResultSet rs) {

        TipoMoneda Dolar = new TipoMoneda(1,"USD","DOLAR","$",
                new Moneda("1"),MonedaUtil.formatoUsd);
        Dolar.setEsMonedaBase(true);

        Factura fac = new Factura(Dolar);
        try {

            Cliente cli = Operaciones.buscarClienteCedula(rs.getString("idcliente"));

            fac.setId(rs.getInt("id"));
            fac.setNumeroFactura(rs.getInt("numero"));
            fac.setCliente(cli);
            fac.setNumeroCaja(rs.getInt("caja"));
            fac.setImprimible(rs.getBoolean("imprime"));
            fac.setActiva(rs.getBoolean("activa"));
            fac.setPagada(rs.getBoolean("pagada"));
            fac.setCancelada(rs.getBoolean("cancelada"));
            fac.setError(rs.getBoolean("error"));
            fac.setEspera(rs.getBoolean("espepra"));
            fac.setIdTasa(rs.getInt("idtasa"));
            Date fec = rs.getDate("fecha");


            Calendar fecha = Calendar.getInstance();
            fecha.setTime(fec);

            fac.setFecha(fecha);


        } catch (Exception e) {

        }
        return fac;
    }

    public static Factura BuscarFacturaActiva() {
        Factura factura = null;

        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(Constantes.SQL_FACTURAS_ACTIVA);
            factura = ResultToFactura(rs);
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return factura;
    }

    public static List<Factura> BuscarFacturaEspera() {
        List<Factura> facturas = new ArrayList<>() ;

        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(Constantes.SQL_FACTURAS_ESPERA);
            while (rs.next()) {
                facturas.add(ResultToFactura(rs));
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return facturas;


    }

    public static Factura UltimaFacturaActiva() {
        Factura factura = BuscarFacturaActiva();
        return factura;
    }

    public static Factura CrearFactura(TipoMoneda mon, Integer idtasa) {
        Factura fac = new Factura(mon);
        fac.setIdTasa(idtasa);
        fac.setCliente(new Cliente());
        //Operaciones.IncrementaConsecutivo(Constantes.SQL_INCREMENTA_FACTURA);
        //Integer numFactura = UltimoNumeroFactura();
        Integer lastId = InsertarFactura(fac);
        fac.setNumeroFactura(lastId);
        fac.setId(lastId );

        return fac;
    }

    public static Integer NuevaFacturaFiscal() {

        Operaciones.IncrementaConsecutivo(Constantes.SQL_INCREMENTA_FACTURA);
        Integer numFactura = UltimoNumeroFactura();

        return numFactura;
    }

    public static Integer UltimoNumeroFactura() {
        Integer lastId = 0;

        try {
            Connection conn = connect(Constantes.dbPrincipal);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(Constantes.SQL_ULTIMA_FACTURA);
            while (rs.next()) {
                lastId = rs.getInt(1);
                System.out.println("FACTURA NUMERO = " + lastId);
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lastId;
    }

    public static boolean InsertarProducto(Producto pro) {
        boolean exito = false;

        Connection conn;
        try {
            conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(Constantes.SQL_INSERTAR_PRODUCTO);

            pstmt.setInt(1,pro.getIdTipoProducto());
            pstmt.setString(2,pro.getDescripcion());
            pstmt.setString(3,pro.getImagenurl());
            pstmt.setInt(4,pro.getIdImpuesto());
            pstmt.setInt(5,pro.getIdCategoria());
            pstmt.setInt(6,pro.getIdMarca());
            pstmt.setString(7,pro.getUnMedida());
            pstmt.setDouble(8,pro.getPrecio().getValor().doubleValue());
            pstmt.setDouble(9,pro.getCosto().getValor().doubleValue());
            pstmt.setDouble(10,pro.getStock());
            pstmt.setString(11,pro.getReferencia());
            pstmt.setString(12,pro.getRefprov());
            pstmt.setString(13,pro.getCodigo());
            pstmt.setInt(14,pro.getIdProveedor());


            pstmt.execute();
            exito = true;
        }catch (Exception e) {
           System.out.println(e.getMessage());
        }
      return exito;
    }

    public static Integer InsertarFactura(Factura fac) {

        Integer lastId = 0;
        try {

            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(Constantes.SQL_INSERTAR_FACTURA);

            pstmt.setInt(1, fac.getNumeroFactura());
            pstmt.setInt(2, fac.getCliente().getId());
            pstmt.setString( 3, fac.getTipoMoneda().getCodmoneda());
            pstmt.setInt(4, fac.getNumeroCaja());
            pstmt.setDouble(5, fac.getTotales().getMontoTotal().getValor().doubleValue());
            pstmt.setDouble(6, fac.getTotales().getMontoImpuesto().getValor().doubleValue());
            pstmt.setDouble(7, fac.getTotales().getMontoBase().getValor().doubleValue());
            pstmt.setDouble(8, fac.getTotales().getMontoDescuento().getValor().doubleValue());
            pstmt.setBoolean(9, fac.getImprimible() );
            pstmt.setBoolean(10, fac.getActiva() );
            pstmt.setBoolean(11, fac.getPagada() );
            pstmt.setBoolean(12, fac.getCancelada() );
            pstmt.setBoolean(13, fac.getError() );
            pstmt.setBoolean(14, fac.getEspera() );
            pstmt.setString(15, Util.calendarToSql(fac.getFecha()));
            pstmt.setString(16, Util.calendarToHora(fac.getFecha()));
            pstmt.setDouble(17,fac.getIdTasa());
            pstmt.execute();
            pstmt.close();

            lastId = Tabla.lastId(conn,"factura");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Last ID="+lastId);

        return lastId;

    }

    public static List<LineaFactura> ObtenerLineasFactura( int idfactura ) {
        return getLineasFactura(idfactura,Constantes.SQL_OBTENER_LINEA_FACTURA);
    }
    public static List<LineaFactura> ObtenerLineasFacturaAgrupado( int idfactura ) {
        return getLineasFactura(idfactura,Constantes.SQL_OBTENER_LINEA_FACTURA_AGRUPADO);
    }

    public static List<LineaFactura> getLineasFactura( int idfactura,String sql) {
        List<LineaFactura> lineas = new ArrayList<>() ;

        Connection conn = connect(Constantes.dbPrincipal);

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,idfactura);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int idpro = rs.getInt("idproducto");
                Producto pro = buscarProductoId(idpro);
                pro.setPrecio(new Moneda(rs.getDouble("precio")));
                LineaFactura linea = new LineaFactura(rs.getInt("id"),pro,rs.getDouble("cantidad"));
                linea.setEstatus(rs.getInt("estatus"));
                lineas.add(linea);

            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lineas;
    }

    public static List<Pago> ObtenerPagosFactura( Factura fac) {
        List<Pago> lineas = new ArrayList<>() ;

        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(Constantes.SQL_BUSCAR_PAGOS+fac.getId());
            while (rs.next()) {

                Pago pag = new Pago(fac.getTipoMoneda(),
                        new Moneda(rs.getDouble("monto")),
                        new Moneda(rs.getDouble("vuelto")));
                pag.setReferencia(rs.getString("referencia"));
                lineas.add(pag);

            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lineas;
    }

    public static boolean InsertarLineaFactura(LineaFactura lin, int idfactura) {
        boolean Exito=false;

        try {
            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(Constantes.SQL_INSERTAR_LINEA_FACTURA);

                pstmt.setInt(1, idfactura);
                pstmt.setInt(2, lin.getProducto().getId());
                pstmt.setString(3, lin.getProducto().getReferencia());
                pstmt.setString(4, lin.getCodigo());
                pstmt.setDouble(5, lin.getCantidad());
                pstmt.setDouble(6, lin.getPreciobase().getValor().doubleValue());
                pstmt.setDouble(7, lin.getProducto().getAlicuota().getValor().doubleValue());
                pstmt.setDouble(8, lin.getDescuento().getValor().doubleValue());
                pstmt.setInt(9, lin.getEstatus());
                pstmt.execute();

            pstmt.close();


            Exito = true;
           /* try {
                conn.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }*/

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Exito;

    }

    public static boolean ActualizarLineaFactura(LineaFactura lin) {
        boolean Exito=false;

        try {
            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(Constantes.SQL_ACTUALIZAR_LINEA_FACTURA);

            pstmt.setDouble(    1, lin.getCantidad());
            pstmt.setDouble(2, lin.getPreciobase().getValor().doubleValue());
            pstmt.setDouble(3, lin.getDescuento().getValor().doubleValue());
            pstmt.setInt(4, lin.getEstatus());
            pstmt.setInt(5, lin.getId());
            pstmt.execute();

            pstmt.close();


            Exito = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Exito;

    }


    public static boolean actualizarProducBuscar() {
        boolean Exito=false;
        List<ProductoBuscar> productos = new ArrayList<>();

        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id,descrip,codigo,ref FROM productos  " );


            while (rs.next()) {

                ProductoBuscar pro = new ProductoBuscar(rs.getInt("id"),
                        rs.getString("descrip"),
                        rs.getString("codigo"),
                        rs.getString("ref"));
                productos.add(pro);

            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        insertarProducBuscar(productos);

        return Exito;
    }

    public static boolean insertarProducBuscar(List<ProductoBuscar> lista) {
        boolean Exito=false;

        try {
            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(Constantes.SQL_INSERTAR_PRODUC_BUSCAR);

            for (ProductoBuscar pro : lista) {

               pstmt.setInt(1, pro.getId());
               pstmt.setString(2, pro.getDescripcion());
               pstmt.setString(3, pro.getRef());
               pstmt.setString(4, pro.getCodigo());

               pstmt.execute();
            }
            pstmt.close();

            Exito = true;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Exito;

    }


    public static boolean ActualizaEstatusFactura(Factura fac) {
        boolean Exito=false;

        FacturaTotal tot = fac.getTotales();

        try {
            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(Constantes.SQL_ACTUALIZAR_FACTURA);

            pstmt.setDouble(1, tot.getMontoTotal().getValor().doubleValue());
            pstmt.setDouble(2, tot.getMontoImpuesto().getValor().doubleValue());
            pstmt.setDouble(3, tot.getMontoBase().getValor().doubleValue());
            pstmt.setDouble(4, tot.getMontoDescuento().getValor().doubleValue());
            if (fac.getCliente()!=null) {
                pstmt.setInt(5, fac.getCliente().getId());

            }
            pstmt.setBoolean(6, fac.getImprimible() );
            pstmt.setBoolean(7, fac.getActiva() );
            pstmt.setBoolean(8, fac.getPagada() );
            pstmt.setBoolean(9, fac.getCancelada() );
            pstmt.setBoolean(10, fac.getError() );
            pstmt.setBoolean(11, fac.getEspera() );
            pstmt.setInt(12,fac.getNumeroFactura());
            pstmt.setInt(13, fac.getId());

            pstmt.execute();
            pstmt.close();


            Exito = true;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Exito;

    }


    public static boolean InsertarPagoFactura( Pago pag, int idfactura) {
        boolean Exito=false;

        try {

            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(Constantes.SQL_INSERTAR_PAGO);

             pstmt.setInt(1, idfactura);
             pstmt.setString(2, pag.getTipoMoneda().getCodmoneda());
             pstmt.setDouble(3,pag.getMonto().getValor().doubleValue());
             pstmt.setDouble(4,pag.getVuelto().getValor().doubleValue());
             pstmt.setDouble(5,pag.getTotal().getValor().doubleValue());
             pstmt.setString(6,pag.getReferencia());
             pstmt.setInt(7, pag.getBanco().getId());
             pstmt.setString(8, Util.calendarToSql(pag.getFechapago()));
             pstmt.setString(9, Util.calendarToHora(pag.getFechapago()));
             pstmt.setString(10, Util.calendarToSql(pag.getFechareg()));
             pstmt.setString(11, Util.calendarToHora(pag.getFechareg()));
             pstmt.setDouble(12, pag.getTipoMoneda().getTasacambio().getValor().doubleValue());
             pstmt.execute();

            pstmt.close();
            Exito = true;
            /*try {
                conn.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());

            }*/

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return Exito;

    }

    public static boolean LimpiarPagoFactura(  int idfactura) {
        boolean Exito=false;

        try {

            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(Constantes.SQL_BORRAR_PAGOS);

            pstmt.setInt(1, idfactura);
            pstmt.execute();

            pstmt.close();
            Exito = true;
            /*try {
                conn.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());

            }*/

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return Exito;

    }

    //<editor-fold desc="Busqueda de Productos">

    public static List<ProductoBuscar> buscarProductoDescrip(String desc) {
        List<ProductoBuscar> lista = new ArrayList<>();
        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT producbuscar.id," +
                    "producbuscar.descrip," +
                    "producbuscar.codigo," +
                    "producbuscar.ref,precio,stock  " +
                    "FROM producbuscar " +
                    "LEFT JOIN productos ON producbuscar.id = productos.id " +
                    "WHERE producbuscar.descrip MATCH '"+desc.trim()+"'  ");

            while (rs.next()) {

                ProductoBuscar pro = new ProductoBuscar(rs.getInt("id"),
                        rs.getString("descrip"),
                        rs.getString("codigo"),
                        rs.getString("ref"));

                pro.setStock(rs.getDouble("stock"));
                pro.setPrecio(new Moneda(rs.getDouble("precio")));

                lista.add(pro);
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;


    }

    public static Producto ResulToProducto(ResultSet rs) {

        Producto pro = new Producto();
        try {
            pro.setId(rs.getInt("id"));
            pro.setIdTipoProducto(rs.getInt("idtipoprod"));
            pro.setDescripcion(rs.getString("descrip"));
            pro.setImagenurl(rs.getString("imagen"));
            pro.setIdImpuesto(rs.getInt("idtipoimp"));
            pro.setIdCategoria(rs.getInt("idcategoria"));
            pro.setIdMarca(rs.getInt("idmarca"));
            pro.setUnMedida(rs.getString("unmedida"));
            pro.setAlicuota(new Moneda(rs.getDouble("alicuota")));
            pro.setPrecio(new Moneda(rs.getDouble("precio")));
            pro.setCosto(new Moneda(rs.getDouble("costo")));

            pro.setStock(rs.getDouble("stock"));
            pro.setReferencia(rs.getString("ref"));
            pro.setCodigo(rs.getString("codigo"));
            pro.setRefprov(rs.getString("refprov"));

            ArrayList barras = new ArrayList();
            barras.add(rs.getString("codigo"));

            pro.setCodbarra(barras);
            pro.setIdProveedor(rs.getInt("idprov"));
        } catch (Exception e) {
            System.out.println("Error en ResulToProducto :"+e.getMessage());
        }

        return pro;

    }

    public static Producto buscarProductoCodigo(String codigo) {
        Producto pro = null;
        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM productos "
                    +" LEFT JOIN tabla_tasaimp ON productos.idtipoimp=tabla_tasaimp.id "
                    +" WHERE codigo='"+codigo.trim()+"' OR ref='"+codigo.trim()+"'");

            while (rs.next()) {
                pro = ResulToProducto(rs);

            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pro;


    }

    public static void borrarProductosImportar() {
        try {
            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM productos_importar");
            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Producto> consultarImportarProducto() {
        List<Producto> lista = new ArrayList<>();
        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM productos_importar ");

            while (rs.next()) {
                Producto pro = ResulToProducto(rs);
                lista.add(pro);

            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;


    }

    public static Producto buscarProductoId(int id) {
        Producto pro = null;
        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM productos "
                    +" LEFT JOIN tabla_tasaimp ON productos.idtipoimp=tabla_tasaimp.id WHERE productos.id="+id);

            while (rs.next()) {
                pro = ResulToProducto(rs);

            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pro;

    }

    //</editor-fold>"

    //<editor-fold desc="Modulos de Cliente>"

    public static boolean InsertarCliente(Cliente cli) {
        boolean Exito=false;
        String Dir2="";
        String Dir3="";

        String Tel2="";
        String Tel3="";

        if (cli.getDirecciones().size()>=2) {
            Dir2 = cli.getDirecciones().get(1).getTexto();
        }
        if (cli.getDirecciones().size()==3) {
            Dir3 = cli.getDirecciones().get(2).getTexto();
        }

        if (cli.getTelefonos().size()>=2) {
            Tel2 = cli.getTelefonos().get(1).toString();
        }

        if (cli.getTelefonos().size()==2) {
            Tel3 = cli.getTelefonos().get(2).toString();
        }

        try {

            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(Constantes.SQL_INSERTAR_CLIENTE);


                pstmt.setString(1, cli.getTiporif());
                pstmt.setInt(2, cli.getRif());
                pstmt.setInt(3, cli.getRif());
                pstmt.setString(4, cli.getRazonsocial());
                pstmt.setString(5, cli.getDirecciones().get(0).getTexto());
                pstmt.setString(6, Dir2);
                pstmt.setString(7, Dir3);
                pstmt.setString(8, cli.getTelefonos().get(0).toString());
                pstmt.setString(9, Tel2);
                pstmt.setString(10, Tel3);
                pstmt.setString(11, cli.getCorreo());
                pstmt.execute();

            pstmt.close();
            Exito = true;
           /* try {
                conn.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());

            }*/

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



        return Exito;
    }

    public static Cliente buscarClienteCedula(String cedula) {
        Cliente cli =null;
        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM clientes " +
                    "WHERE cedula="+cedula+" OR rif="+cedula+" OR id="+cedula);
            while (rs.next()) {
                cli =  new Cliente();

                List<Direccion> dirs = new ArrayList<>();
                dirs.add( new Direccion(rs.getString("dir1")));
                dirs.add( new Direccion(rs.getString("dir2")));
                dirs.add( new Direccion(rs.getString("dir3")));

                List<Telefono> tels =  new ArrayList<>();
                Arrays.asList(new Telefono("212","00000"));

                cli = new Cliente(rs.getInt("id"),
                        rs.getString("razonsoc"),
                        rs.getInt("rif"),
                        rs.getString("tipo"),dirs,tels);

            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cli;
    }

    //</editor-fold >"


    //<editor-fold desc="Movimientos de Inventario>"
    public static boolean insertarMovimientoInventario(MovInventario mov) {
        boolean Exito=false;

        try {
            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(Constantes.SQL_INSERTAR_MOVINV);

            pstmt.setInt(1, mov.getIdtipomov());
            pstmt.setInt(2, mov.getIdproducto());
            pstmt.setDouble(3, mov.getCantidad());
            pstmt.setString(4, Util.calendarToSql(mov.getFecha()));

            pstmt.execute();

            pstmt.close();

            Exito = true;
            /*try {
                conn.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());

            }*/

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(Constantes.SQL_ACTUALIZAR_STOCK);

            pstmt.setDouble(1, mov.getCantidad());
            pstmt.setInt(2, mov.getIdproducto());

            pstmt.execute();
            pstmt.close();
            Exito = true;
            /*try {
                conn.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }*/

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Exito;

    }

    //</editor-fold>"

}
