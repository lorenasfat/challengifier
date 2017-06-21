package com.example.lorena.challengifier.services.business.services;

import java.text.SimpleDateFormat;

/**
 * Created by Lorena on 17.01.2017.
 */

public class Validator {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
    static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean isEmpty(String input){
        if(input.isEmpty())
            return true;
        return false;
    }

    public static boolean isDate(String input){
        try{
            sdf.parse(input);
            return true;
        }
        catch (Exception ex){
            try{
                sdf2.parse(input);
                return true;
            }
            catch(Exception e){
                return false;
            }
        }
    }
}
