package com.example.lorena.challengifier.utils.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lorena on 15.01.2017.
 */

public class DateFormatter {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
    static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    public static Date getDate(String dateString){
        Date date = new Date();
        try{
            date = sdf.parse(dateString);
        }
        catch (Exception e){
            try{
                date = sdf2.parse(dateString);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return date;
    }
}
