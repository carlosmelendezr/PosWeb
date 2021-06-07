package modelos.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Tabla {

    public static void crear(Connection conn, String sql) {



        try {
             Statement stmt = conn.createStatement();

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
