package modelos.datos;

import modelos.factura.Factura;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Operaciones {



    public static boolean InsertarFactura(Factura fac) {
        boolean exito = false;

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


            pstmt.executeUpdate();
            exito = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return exito;
    }

}
