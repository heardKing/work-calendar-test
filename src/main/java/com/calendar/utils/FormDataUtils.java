package com.calendar.utils;

import com.calendar.model.WorkCalendar;
import com.calendar.model.WorkShift;
import com.calendar.model.WorkingDay;

import java.util.Calendar;

public class FormDataUtils {

    private static int code = 1;

    public static WorkingDay getWorkingDay(int year,int month,int date,WorkShift workShift,WorkCalendar workCalendar){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,date);
        WorkingDay workingDay = new WorkingDay();
        workingDay.setWorkingDate(calendar.getTime());
        workingDay.setDateType("工作日");
        workingDay.setWorkShift(workShift);
        workingDay.setWorkCalendar(workCalendar);
        return workingDay;
    }

    public static WorkShift getWorkShift(){
        WorkShift workShift = new WorkShift();
        workShift.setShiftCode("shiftCode-" + String.format("%04d",code++));
        workShift.setShiftName("shiftName-" + String.format("%04d",code++));
        return workShift;
    }

    public static WorkCalendar getTemplateWorkCalendar(WorkCalendar template, WorkShift workShift){
        WorkCalendar workCalendar = new WorkCalendar();
        initCalendar(workCalendar);
        workCalendar.setCalendarTemplate(template);
        workCalendar.setWorkShift(workShift);
        workCalendar.setIsTemplate("1");
        return workCalendar;
    }
    public static WorkCalendar getNormalWorkCalendar(WorkCalendar template, WorkShift workShift){
        WorkCalendar workCalendar = new WorkCalendar();
        initCalendar(workCalendar);
        workCalendar.setCalendarTemplate(template);
        workCalendar.setWorkShift(workShift);
        workCalendar.setIsTemplate("0");
        return workCalendar;
    }

    private static WorkCalendar initCalendar(WorkCalendar workCalendar){
        Calendar calendar = Calendar.getInstance();
        workCalendar.setCalendarCode("code-"+ String.format("%04d",code++));
        workCalendar.setCalendarName("name-"+ String.format("%04d",code++));

        calendar.set(2017,0,1);
        workCalendar.setStartDate(calendar.getTime());
        calendar.set(2017,11,31);
        workCalendar.setEndDate(calendar.getTime());

        workCalendar.setRestDayRule("1,2,3,4,5");
        workCalendar.setIsAdministration("0");
        workCalendar.setIsProduction("0");
        workCalendar.setIsEquipment("0");
        workCalendar.setIsTemplate("0");
        return workCalendar;
    }

}
