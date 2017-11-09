package com.calendar.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date getDate(String data){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertDate = null;
        try {
             convertDate = sdf.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertDate;
    }

}
