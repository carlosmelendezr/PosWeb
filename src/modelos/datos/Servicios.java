package modelos.datos;

import modelos.factura.MovInventario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Scanner;

public class Servicios {

    public static void main(String arg[]) {
        Servicios svr = new Servicios();
        //svr.importarProductos("c:\\tmp\\catbuscar.csv","productos");
        svr.importarMovInv("c:\\temp\\movprueba.txt");

    }




    public void importarProductos(String FileName, String tabla) {
        Connection conn;
        StringBuilder sql = new StringBuilder();
        StringBuilder campos = new StringBuilder();
        campos.append("INSERT INTO "+tabla+" (");

        try {

            conn = Connect.connect(Constantes.dbPrincipal);
            Statement comando = conn.createStatement() ;

            BufferedReader br = new BufferedReader(new FileReader(FileName));
            String line;
            int count = 0;
            int max = 0;
            while ((line = br.readLine()) != null) {
                sql = new StringBuilder();
                Scanner scan = new Scanner(line);

                scan.useDelimiter(",");
                int secuencia = 0;
                while (scan.hasNext()) {
                    String dato = scan.next();
                    dato = dato.trim();

                    if (count == 0) {

                        System.out.println("Campo " + dato + " indice " + secuencia);

                        if (!dato.isEmpty() && secuencia >0) campos.append(",");
                        campos.append(dato);
                        max ++;
                    }   else {

                        sql.append(dato.replace("\"","'"));
                        if (secuencia < max-1) sql.append(",");
                    }

                    secuencia++;

                }
                if (count==0) campos.append(") VALUES (");
                count ++;
                if (count > 0) {
                    sql.append(")");
                    String SQL_INSERT = campos.toString() + sql.toString();
                    System.out.println(SQL_INSERT);
                    try {
                        comando.execute(SQL_INSERT);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }

            }
            conn.close();

        } catch (Exception e) {
            System.out.println("Error :"+e.getMessage());
        }



    }

    public void importarMovInv(String FileName) {

        try {


            BufferedReader br = new BufferedReader(new FileReader(FileName));
            String line;
            int count = 0;
            int max = 0;
            while ((line = br.readLine()) != null) {

                  System.out.println("Linea:"+line);
                  String[] parts = line.split(",");

                  MovInventario mov = new MovInventario();
                  mov.setIdtipomov(Integer.parseInt(parts[0]));
                  mov.setIdproducto(Integer.parseInt(parts[1]));
                  mov.setCantidad(Double.parseDouble(parts[2]));
                  mov.setFecha(Calendar.getInstance());
                  Operaciones.insertarMovimientoInventario(mov);

            }


        } catch (Exception e) {
            System.out.println("Error :"+e.getMessage());
        }
    }
}