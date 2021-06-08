package modelos.datos;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {

    public static String calendarToSql(Calendar c) {
        String dateForMySql;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateForMySql = sdf.format(c.getTime());
        return dateForMySql;
    }
}
