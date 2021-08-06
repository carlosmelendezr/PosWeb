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

            Tabla.crearBatch(conn,Constantes.crearTablasFactura());
            Tabla.crearBatch(conn,Constantes.crearTablasProductos());
            Tabla.crearBatch(conn,Constantes.crearTablasClientes());

            try {
                conn.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());

            }
        }
    }

