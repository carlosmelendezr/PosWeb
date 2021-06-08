package modelos.datos;

import modelos.factura.Factura;

import java.sql.*;

public class Operaciones {



    public static Integer InsertarFactura(Factura fac) {
        boolean exito = false;
        Integer lastId = 0;
        try {

            Connection conn = Connect.connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(Constantes.SQL_INSERTAR_FACTURA,
                    Statement.RETURN_GENERATED_KEYS);


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

            exito = true;
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

}
