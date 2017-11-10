package com.calendar.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    /**
     *
     * <p>Title: checkHoliday </P>
     * <p>Description: TODO 验证日期是否是节假日</P>
     * @param calendar  传入需要验证的日期
     * @return
     * return boolean    返回类型  返回true是节假日，返回false不是节假日
     * throws
     * date 2014-11-24 上午10:13:07
     */
    public static boolean checkHoliday(Calendar calendar){

        //判断日期是否是周六周日
        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            return true;
        }
        return false;
    }

}
