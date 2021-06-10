package modelos.datos;

import modelos.factura.Factura;
import modelos.factura.LineaFactura;
import modelos.factura.Pago;

import java.sql.*;
import java.util.List;

public class Operaciones {

    public static Integer CrearFactura(Factura fac) {
        Integer lastId = InsertarFactura(fac);
        if (lastId > 0) {
            InsertarLineaFactura(fac.getLineas(),lastId);
            InsertarPagoFactura(fac.getPagos(),lastId);
        }
        return lastId;
    }



    public static Integer InsertarFactura(Factura fac) {

        Integer lastId = 0;
        try {

            Connection conn = Connect.connect(Constantes.dbPrincipal);
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

            Connection conn = Connect.connect(Constantes.dbPrincipal);
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

            Connection conn = Connect.connect(Constantes.dbPrincipal);
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

}
