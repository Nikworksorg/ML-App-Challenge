package com.appchallenge.ml_app_challenge.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nikhilthiruvengadam on 12/20/17.
 */

public class DateUtils {
    public static String formatDate(String sDate){
        Date parsedDate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            parsedDate = simpleDateFormat.parse(sDate);
            simpleDateFormat = new SimpleDateFormat("EEE, MMMM d, yyyy");
            sDate= simpleDateFormat.format(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sDate;
    }

    public static boolean isValidDate(String sDate){
        Date parsedDate = null;
        Date todayDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            parsedDate = simpleDateFormat.parse(sDate);
            if(todayDate.compareTo(parsedDate) >= 0 ){
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
