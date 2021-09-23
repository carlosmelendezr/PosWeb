package modelos.datos;

import modelos.factura.Banco;
import modelos.factura.Moneda;
import modelos.factura.Tasa;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static modelos.datos.Connect.connect;

public class dboBanco {

    public static String SQL_CREAR_TABLA_BANCO = "CREATE TABLE IF NOT EXISTS bancos ("
            + "id integer PRIMARY KEY,"
            + "codigo text, "
            + "descrip text, "
            + "activo integer )";

    public static String SQL_CARGAR_BANCOS = "SELECT * FROM bancos WHERE activo=1";

    public static String SQL_INSERTAR_BANCO_INICIAL = "INSERT INTO BANCOS (id,codigo,descrip,activo) " +
            "VALUES " +
            "(1,'VISA','VISA',1)," +
            "(2,'MASTER','MASTER CARD',1)," +
            "(3,'MAESTRO','MAESTRO',1), "+
            "(4,'TRANSF','TRANSFERENCIA',1), "+
            "(5,'MOVIL','PAGO MOVIL',1), "+
            "(6,'ZELLE','ZELLE',1) ";


    public static String SQL_INSERTAR_BANCO = "INSERT INTO bancos " +
            "(codigo,descrip,activo) "+
            "VALUES ( ?, ?, ? )";


    public static List<String> crearTablasBancos() {
        List<String> queris = new ArrayList<>();
        queris.add(SQL_CREAR_TABLA_BANCO);
        queris.add(SQL_INSERTAR_BANCO_INICIAL);
        return queris;
    }

    public static boolean InsertarBanco(Banco ban ) {
        boolean Exito=false;

        try {

            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERTAR_BANCO);

            pstmt.setString(1, ban.getCodigo());
            pstmt.setString(2, ban.getDescripcion());
            pstmt.setInt(3,ban.getActivo());

            pstmt.execute();

            pstmt.close();
            Exito = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Exito;
    }


    public static List<Banco> cargarBancos() {
        List<Banco> lista =null;
        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement(SQL_CARGAR_BANCOS);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Banco ban =  new Banco();

                ban.setId(rs.getInt("id"));
                ban.setCodigo(rs.getString("codigo"));
                ban.setDescripcion(rs.getString("descripcion"));
                lista.add(ban);
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

}
