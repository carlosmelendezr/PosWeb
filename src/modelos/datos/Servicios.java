package modelos.datos;

import modelos.factura.MovInventario;
import modelos.factura.Producto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Servicios {

    public static void main(String arg[]) {
        Servicios svr = new Servicios();
        //svr.importarProductos("c:\\tmp\\catbuscar.csv","productos");
        List<MovInventario> lista = svr.leerCsvMovInv("c:\\temp\\ingreso.csv");
        svr.importarMovInv(lista);
        //svr.importarMovInv("c:\\temp\\ingreso.csv");

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

                scan.useDelimiter(";");
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

    public List<MovInventario> leerCsvMovInv(String FileName) {
        List<MovInventario> lista = new ArrayList<>();

        Integer tipoMov=0;
        Double cantidad=0.0;
        String codigo = "";
        Producto pro = null;
        int count = 0;


        StringBuilder linea;
        try {
            BufferedReader br = new BufferedReader(new FileReader(FileName));
            String readline;

            while ((readline = br.readLine()) != null) {
                linea = new StringBuilder();
                Scanner scan = new Scanner(readline);
                scan.useDelimiter(";");

                count = 0;
                tipoMov=0;
                cantidad=0.0;
                codigo = "";
                pro = null;

                while (scan.hasNext()) {
                    String dato = scan.next();
                    dato = dato.trim();

                    //System.out.println("Linea:"+readline+", Dato "+dato+" count="+count);


                    switch (count) {
                        case 0: {
                            tipoMov = Integer.parseInt(dato);
                            break;
                        }
                        case 1: {
                            pro = Operaciones.buscarProductoCodigo(dato);
                            break;
                        }
                        case 2: {
                            cantidad = Double.parseDouble(dato);
                            break;
                        }
                    }
                        if (count > 2 && tipoMov > 0 && pro!=null || cantidad!=0) {
                            MovInventario mov = new MovInventario();

                            mov.setIdtipomov(tipoMov);
                            mov.setIdproducto(pro.getId());
                            mov.setCantidad(cantidad);
                            mov.setFecha(Calendar.getInstance());
                            lista.add(mov);
                        }
                    count ++;

                    }


                }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }


    public void importarMovInv(List<MovInventario> lista) {

          for(MovInventario mov:lista) {
              Operaciones.insertarMovimientoInventario(mov);
          }

            /*BufferedReader br = new BufferedReader(new FileReader(FileName));
            String line;
            int count = 0;
            int max = 0;
            while ((line = br.readLine()) != null) {

                  System.out.println("Linea:"+line);
                  String[] parts = line.split(";");
                  if (parts.length==0) {
                      System.out.println("Separado por ;");
                      parts = line.split(";");
                  }

                  String codigo = parts[1];


                  if (pro!=null || parts.length>0) {

                      MovInventario mov = new MovInventario();

                      mov.setIdtipomov(Integer.parseInt(parts[0]));
                      mov.setIdproducto(pro.getId());
                      mov.setCantidad(Double.parseDouble(parts[2]));
                      mov.setFecha(Calendar.getInstance());

                      Operaciones.insertarMovimientoInventario(mov);
                  }

            }


        } catch (Exception e) {
            System.out.println("Error :"+e.getMessage());
        }*/
    }
}