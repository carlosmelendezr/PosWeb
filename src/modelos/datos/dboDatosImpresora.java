package modelos.datos;

import modelos.factura.Banco;
import servicios.impresion.DatosImpresora;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static modelos.datos.Connect.connect;

public class dboDatosImpresora {

    public static String SQL_CREAR_TABLA_IMPRESORA = "CREATE TABLE IF NOT EXISTS impresora ("
            + "id integer PRIMARY KEY,"
            + "puerto text, "
            + "marca text, "
            + "modelo text, "
            + "serial text, "
            + "maxdigpre integer, "
            + "activo integer )";

    public static String SQL_CARGAR_IMPRESORA = "SELECT * FROM impresora WHERE activo=1 ORDER BY id DESC LIMIT 1";

    public static String SQL_INSERTAR_IMPRESORA_INICIAL = "INSERT INTO impresora (id,puerto,marca,modelo,serial,maxdigpre,activo) " +
            "VALUES " +
            "(1,'COM6','BIXOLON','SRP-270','Z1A8116194',10,1)";



    public static String SQL_INSERTAR_IMPRESORA = "INSERT INTO impresora " +
            "(puerto,marca,modelo,serial,maxdigpre,activo) "+
            "VALUES ( ?, ?, ?, ?, ?, ? )";


    public static List<String> crearTablaImpresora() {
        List<String> queris = new ArrayList<>();
        queris.add(SQL_CREAR_TABLA_IMPRESORA);
        queris.add(SQL_INSERTAR_IMPRESORA_INICIAL);
        return queris;
    }

    public static boolean InsertarImpresora(DatosImpresora imp) {
        boolean Exito=false;

        try {

            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERTAR_IMPRESORA);

            pstmt.setString(1, imp.getPuerto());
            pstmt.setString(2, imp.getMarca());
            pstmt.setString(3,imp.getSerial());
            pstmt.setInt(4,imp.getMaximoDigitosPrecio());
            pstmt.setInt(5,1);

            pstmt.execute();

            pstmt.close();
            Exito = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Exito;
    }


    public static DatosImpresora cargarImpresora() {
        DatosImpresora imp = null;
        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement(SQL_CARGAR_IMPRESORA);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                imp = new DatosImpresora();
                imp.setId(rs.getInt("id"));
                imp.setPuerto(rs.getString("puerto"));
                imp.setMarca(rs.getString("marca"));
                imp.setModelo(rs.getString("modelo"));
                imp.setSerial(rs.getString("serial"));
                imp.setMaximoDigitosPrecio(rs.getInt("maxdigpre"));
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return imp;
    }


}
