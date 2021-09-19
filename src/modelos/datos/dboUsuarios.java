package modelos.datos;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static modelos.datos.Connect.connect;

public class dboUsuarios {

    public static Integer USUARIO_ACTIVO = 0;
    public static Integer USUARIO_BLOQUEADO = 1;

    public static String SQL_CREAR_TABLA_USUARIOS = "CREATE TABLE IF NOT EXISTS usuarios ("
            + "id integer PRIMARY KEY,"
            + "cedula integer, "
            + "nombre text, "
            + "clave text, "
            + "idrol integer, "
            + "estatus integer) ";


    public static String SQL_INSERTAR_USUARIO = "INSERT INTO usuarios " +
            "(cedula,nombre,clave,idrol,estatus) "+
            "VALUES (?,?,?,?,?)";

    public static String SQL_VALIDAR_ACCESO = "SELECT * FROM usuarios WHERE cedula=? and clave=?";
    public static String SQL_LISTAR_USUARIOS = "SELECT * FROM usuarios ORDER BY nombre";

    public static String SQL_IND_USUARIOS_CED = "CREATE UNIQUE INDEX  iusrced ON usuarios (cedula)";

    public static List<String> crearTablasUsuarios() {
        List<String> queris = new ArrayList<>();
        queris.add(SQL_CREAR_TABLA_USUARIOS);
        queris.add(SQL_IND_USUARIOS_CED);

        return queris;

    }

    public static boolean InsertarUsuario(Usuario usr) {
        boolean Exito=false;

        try {

            Connection conn = connect(Constantes.dbPrincipal);
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERTAR_USUARIO);

            String clave = getMD5(usr.getClave());

            pstmt.setInt(1, usr.getCedula());
            pstmt.setString(2, usr.getNombre());
            pstmt.setString(3, clave);
            pstmt.setInt(4, usr.getIdrol());
            pstmt.setInt(5, usr.getEstatus());

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

    public static String getMD5(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger bi = new BigInteger(1, md.digest(string.getBytes()));
            return bi.toString(16);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error en MD5"+ex.getMessage());

            return "";
        }
    }

    public static Usuario buscarUsuarioCedula(Integer cedula,String clave) {
        Usuario usr =null;
        Connection conn = connect(Constantes.dbPrincipal);

        try {
            Statement stmt = conn.createStatement();

            PreparedStatement pstmt = conn.prepareStatement(SQL_VALIDAR_ACCESO);

            String claveMd5 = getMD5(clave.trim());

            pstmt.setInt(1, cedula);
            pstmt.setString(2, claveMd5);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                usr =  new Usuario();

                usr.setId(rs.getInt("id"));
                usr.setCedula(rs.getInt("cedula"));
                usr.setNombre(rs.getString("nombre"));
                usr.setIdrol(rs.getInt("idrol"));
                usr.setEstatus(rs.getInt("estatus"));


            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return usr;

    }

}
