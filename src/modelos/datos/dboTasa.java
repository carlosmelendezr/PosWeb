package modelos.datos;

import modelos.factura.Moneda;
import modelos.factura.Tasa;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static modelos.datos.Connect.connect;

public class dboTasa {

    public static String SQL_CREAR_TABLA_TASA = "CREATE TABLE IF NOT EXISTS tasa ("
            + "id integer PRIMARY KEY,"
            + "valor real, "
            + "fecha date) ";


    public static String SQL_INSERTAR_TASA = "INSERT INTO tasa " +
            "(valor,fecha) "+
            "VALUES (?,?)";

    public static String SQL_ULTIMA_TASA = "SELECT * FROM tasa ORDER BY fecha DESC LIMIT 1 ";

    public static List<String> crearTablasTasa() {
        List<String> queris = new ArrayList<>();
        queris.add(SQL_CREAR_TABLA_TASA);
        return queris;
    }

    public static boolean InsertarTasa(Tasa tas ) {
        boolean Exito=false;

        try {

            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERTAR_TASA);

            pstmt.setDouble(1, tas.getValor().getValor().doubleValue());
            pstmt.setString(2, Util.calendarToSql(tas.getFecha()));

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


    public static Tasa buscarUltimaTasa() {
        Tasa tas =null;
        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement(SQL_ULTIMA_TASA);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                tas =  new Tasa();

                tas.setValor(new Moneda(rs.getDouble("valor")));
                Calendar fecha = Calendar.getInstance();
                fecha.setTime(rs.getDate("fecha"));

                tas.setFecha(fecha);

            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tas;

    }


}
