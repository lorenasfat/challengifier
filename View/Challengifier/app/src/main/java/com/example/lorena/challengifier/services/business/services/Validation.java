package com.example.lorena.challengifier.services.business.services;

import java.text.SimpleDateFormat;

/**
 * Created by Lorena on 17.01.2017.
 */

public class Validation {
    static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

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
            return false;
        }
    }
}
