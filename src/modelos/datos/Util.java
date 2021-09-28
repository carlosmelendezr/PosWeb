package modelos.datos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {

    public static String calendarToSql(Calendar c) {
        String dateForMySql;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateForMySql = sdf.format(c.getTime());
        return dateForMySql;
    }

    public static String calendarToHora(Calendar c) {
        String hora;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        hora = sdf.format(c.getTime());
        return hora;
    }

    public static void guardarLogError(String mensaje) {
        File archivo;
        FileOutputStream log=null;
        archivo = new File(Constantes.dirOut+"error.txt");

        try {
            log = new FileOutputStream(archivo);
        } catch (Exception e) {
            System.out.println("Error al crear el archivo "+Constantes.dirOut+"error.txt");
            return;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(log));
        try {
                bw.write(mensaje);
                bw.newLine();
                bw.close();
            log.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al escribir el archivo "+Constantes.dirOut+"error.txt");
        }

    }
}
