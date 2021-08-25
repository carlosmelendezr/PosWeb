package modelos.datos;

import modelos.factura.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static modelos.datos.Connect.connect;

public class Operaciones {

    public static Integer CrearFactura(Factura fac) {
        Integer lastId = InsertarFactura(fac);
        if (lastId > 0) {
            InsertarLineaFactura(fac.getLineas(),lastId);
            InsertarPagoFactura(fac.getPagos(),lastId);
        }
        return lastId;
    }

    public static void InsertarProducto(String sql) {

        Connection conn;
        try {
            conn = connect(Constantes.dbPrincipal);
            Statement comando = conn.createStatement() ;
            comando.execute(sql);
            conn.close();
        }catch (Exception e) {
           System.out.println(e.getMessage());
        }

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
            pstmt.setDouble(17,fac.getTipoMoneda().getTasacambio().getValor().doubleValue());
            pstmt.execute();
            pstmt.close();

            lastId = Tabla.lastId(conn,"factura");

            try {
                  conn.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Last ID="+lastId);

        return lastId;

    }

    public static boolean InsertarLineaFactura(List<LineaFactura> lineas, int idfactura) {
        boolean Exito=false;

        try {

            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(Constantes.SQL_INSERTAR_LINEA_FACTURA);

            for(LineaFactura lin:lineas) {
                pstmt.setInt(1, idfactura);
                pstmt.setInt(2, lin.getProducto().getId());
                pstmt.setString(3, lin.getProducto().getReferencia());
                pstmt.setString(4, lin.getCodbarra());
                pstmt.setDouble(5, lin.getCantidad());
                pstmt.setDouble(6, lin.getPrecio().getValor().doubleValue());
                pstmt.setDouble(7, lin.getProducto().getAlicuota().getValor().doubleValue());
                pstmt.setDouble(8, lin.getDescuento().getValor().doubleValue());
                pstmt.execute();
            }
            pstmt.close();
            Exito = true;
            try {
                conn.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return Exito;

    }

    public static boolean InsertarPagoFactura(List<Pago> pagos, int idfactura) {
        boolean Exito=false;


        try {

            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(Constantes.SQL_INSERTAR_PAGO);

            for(Pago pag:pagos) {
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
            }
            pstmt.close();
            Exito = true;
            try {
                conn.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return Exito;

    }

    public static List<ProductoBuscar> buscarProductoDescrip(String desc) {
        List<ProductoBuscar> lista = new ArrayList<>();
        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT descrip,codigo,ref  " +
                    "FROM producbuscar WHERE descrip MATCH '"+desc.trim()+"'  ");
            while (rs.next()) {
                ProductoBuscar pro = new ProductoBuscar(rs.getString("descrip"),
                        rs.getString("codigo"), rs.getString("ref"));

                lista.add(pro);
                System.out.println(pro.getDescripcion());
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;


    }

    public static Producto buscarProductoCodigo(String codigo) {
        Producto pro = new Producto();
        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * " +
                    "FROM productos WHERE codigo='"+codigo.trim()+"' OR ref='"+codigo.trim()+"'  ");
            while (rs.next()) {



                pro.setId(rs.getInt("id"));
                pro.setIdTipoProducto(rs.getInt("idtipoprod"));
                pro.setDescripcion(rs.getString("descrip"));
                pro.setImagenurl(rs.getString("imagen"));
                pro.setIdImpuesto(rs.getInt("idtipoimp"));
                pro.setIdCategoria(rs.getInt("idcategoria"));
                pro.setIdMarca(rs.getInt("idmarca"));
                pro.setUnMedida(rs.getString("unmedida"));

                Moneda precio = new Moneda();
                precio.setValor(rs.getDouble("precio"));
                pro.setPrecio(precio);

                Moneda costo = new Moneda();
                costo.setValor(rs.getDouble("costo"));
                pro.setCosto(costo);

                pro.setStock(rs.getDouble("stock"));
                pro.setReferencia(rs.getString("ref"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setRefprov(rs.getString("refprov"));

                ArrayList barras = new ArrayList();
                barras.add(rs.getString("codigo"));

                pro.setCodbarra(barras);
                pro.setIdProveedor(rs.getInt("idprov"));


                System.out.println(pro.getDescripcion());
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pro;


    }

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
            try {
                conn.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



        return Exito;
    }

    public static Cliente buscarClienteCedula(String cedula) {
        Cliente cli = new Cliente();
        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM clientes WHERE cedula="+cedula+" OR rif="+cedula);
            while (rs.next()) {


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


                System.out.println(cli.getRazonsocial());
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cli;


    }

}
