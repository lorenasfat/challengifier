package com.example.lorena.challengifier.utils.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lorena on 15.01.2017.
 */

public class DateFormatter {
    static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static Date getDate(String dateString){
        Date date;
        try{
            date = sdf.parse(dateString);
        }
        catch (Exception e){
            return new Date();
        }
        return date;
    }
}
