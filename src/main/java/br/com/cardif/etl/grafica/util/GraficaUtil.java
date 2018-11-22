package br.com.cardif.etl.grafica.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GraficaUtil {

    public static Date parseDate(String data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.parse(data);
    }

    public static String parseString(Date data) throws ParseException {
        if(null != data) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return sdf.format(data);
        }else{
            return "";
        }
    }
}
