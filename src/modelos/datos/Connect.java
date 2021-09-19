package modelos.datos;

import modelos.factura.Moneda;
import modelos.factura.Tasa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

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

            //Tabla.crearBatch(conn,Constantes.crearTablasFactura());
            //Tabla.crearBatch(conn,Constantes.crearTablasProductos());
            //Operaciones.actualizarProducBuscar();
            /*Tabla.crearBatch(conn,Constantes.crearTablasClientes());
            Tabla.crearBatch(conn,Constantes.crearTablasTasaImpuesto());
            Tabla.crearBatch(conn,Constantes.crearConsecutivos());
            Tabla.crearBatch(conn,Constantes.crearTablasMovInv());
            Tabla.crearBatch(conn,dboUsuarios.crearTablasUsuarios());*/

            /*Usuario usr = new Usuario();
            usr.setCedula(12641955);
            usr.setClave("800rosas");
            usr.setNombre("CARLOS MELENDEZ");
            usr.setIdrol(0);
            usr.setEstatus(dboUsuarios.USUARIO_ACTIVO);
            dboUsuarios.InsertarUsuario(usr);*/

            /*Tabla.crearBatch(conn,dboTasa.crearTablasTasa());
            Tasa tas = new Tasa(new Moneda(4.30), Calendar.getInstance());
            dboTasa.InsertarTasa(tas);*/

            try {
                conn.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());

            }
        }
    }

