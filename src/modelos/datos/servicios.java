package modelos.datos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class servicios {

    public static void main(String arg[]) {
        servicios svr = new servicios();

        svr.importarProductos("c:\\tmp\\productos.csv");
    }




    public void importarProductos(String FileName) {

        StringBuilder sql = new StringBuilder();
        StringBuilder campos = new StringBuilder();
        campos.append("INSERT INTO productos (");

        try {
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
                    Operaciones.InsertarProducto(SQL_INSERT);
                }

            }

        } catch (Exception e) {
            System.out.println("Error :"+e.getMessage());
        }



    }
}