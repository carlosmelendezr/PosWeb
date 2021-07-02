package modelos.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class  Connect {

        public static Connection connect(String nombreArchivo) {
            Connection conn = null;
            try {
                // db parameters
                String url = "jdbc:sqlite:"+Constantes.dirLocal+nombreArchivo;
                // create a connection to the database
                conn = DriverManager.getConnection(url);
                conn.setAutoCommit(true);

                System.out.println("La conexion to SQLite ha sido establecida. Archivo "+Constantes.dirLocal+nombreArchivo);

            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            return conn;
        }
        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            Connection conn = connect("posweb.db");
            Tabla.crear(conn,Constantes.SQL_CREAR_FACTURA);
            Tabla.crear(conn,Constantes.SQL_CREAR_LINEAFAC);
            Tabla.crear(conn,Constantes.SQL_CREAR_PAGOS);
            Tabla.crear(conn,Constantes.SQL_CREAR_PRODUCTOS);
            try {
                conn.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());

            }
        }
    }

