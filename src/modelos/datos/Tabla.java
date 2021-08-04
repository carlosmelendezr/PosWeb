package modelos.datos;

import java.sql.*;
import java.util.List;

public class Tabla {

    public static void crearBatch(Connection conn, List<String> sqllist) {
        for (String sql:sqllist) {
            crear(conn,sql);
        }
    }


    public static void crear(Connection conn, String sql) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Sentencia : "+sql);

        }
    }

    public static Integer lastId(Connection conn, String tabla) {
        Integer lastId = 0;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT rowid from "+tabla+" order by ROWID DESC limit 1");
            while (rs.next()) {
                lastId = rs.getInt(1);

                System.out.println("automatically generated key value = " + lastId);
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lastId;
    }
}
