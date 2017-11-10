package com.calendar.repository.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.calendar.model.WorkCalendar;
import com.calendar.model.WorkShift;
import com.calendar.model.WorkingDay;
import com.calendar.repository.WorkCalendarRepository;
import com.calendar.utils.DateUtils;
import com.calendar.utils.FormDataUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.*;

import static org.junit.Assert.*;

public class WorkCalendarRepositoryImplTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void init(){
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry =
                new ServiceRegistryBuilder().applySettings(configuration.getProperties())
                        .buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @After
    public void destroy(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void initDataBaseWorkShift(){
        for (int i = 0;i<5;i++) {
            WorkShift workShiftForCalendar = FormDataUtils.getWorkShift();
            this.session.save(workShiftForCalendar);
        }
    }

    @Test
    public void initDateBaseWorkCalendar(){
        WorkShift workShift = new WorkShift();
        workShift.setWorkShiftID(1l);
        WorkCalendar workCalendar = FormDataUtils.getNormalWorkCalendar(null,workShift);
        this.session.save(workCalendar);
    }

    @Test
    public void initDateBaseWorkingDay(){
        WorkCalendar workCalendar = new WorkCalendar();
        workCalendar.setCalendarID(6l);
        WorkShift workShift = new WorkShift();
        workShift.setWorkShiftID(4l);
        for (int i = 0; i < 4; i++) {
            WorkingDay workingDay = FormDataUtils.getWorkingDay(2017,2,15+i,workShift,workCalendar);
            this.session.save(workingDay);
        }
    }

    @Test
    public void save() throws Exception {
        /*实际程序运行中首先保存工作日历然后再编辑特殊的工作日*/
        WorkShift workShiftForCalendar = FormDataUtils.getWorkShift();
        this.session.save(workShiftForCalendar);
        WorkCalendar workCalendar = FormDataUtils.getNormalWorkCalendar(null,workShiftForCalendar);
        this.session.save(workCalendar);

        WorkShift workShiftForDay = FormDataUtils.getWorkShift();
        this.session.save(workShiftForDay);
        WorkingDay workingDay = FormDataUtils.getWorkingDay(2017,2,15,workShiftForDay,workCalendar);
        this.session.save(workingDay);

    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void getByID() throws Exception {
        /*
        * 前端返回数据后端分页返回给前端，按照每一个月一次返回给前端
        * WorkingDay这个类可以自己修改，
        *currentTime = "2017-03-01"
        * */
        String queryData = "2017-03-01";
        WorkCalendar workCalendar = (WorkCalendar)this.session.get(WorkCalendar.class, 6l);
        List<WorkingDay> workingDays = workCalendar.getWorkingDays();
        WorkShift workShift = workCalendar.getWorkShift();
        Calendar calendar = Calendar.getInstance();
        String[] timeArray = queryData.split("-");
        calendar.set(Integer.parseInt(timeArray[0]),Integer.parseInt(timeArray[1])-1,Integer.parseInt(timeArray[2]),0,0,0);
        calendar.set(Calendar.MILLISECOND,0);
        int mouth = calendar.get(Calendar.MONTH);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Map<Date,WorkingDay> workingDayMap = new TreeMap<Date, WorkingDay>();//TreeMap<Date, WorkingDay>();
        for (int i = 0; i< maxDay; i++){
            Date date = calendar.getTime();
            WorkingDay workingDay = new WorkingDay();
            workingDay.setWorkingDate(date);
            if(DateUtils.checkHoliday(calendar)){
                workingDay.setDateType("休息日");
            }else {
                workingDay.setDateType("工作日");
                workingDay.setWorkShift(workShift);
            }
            workingDayMap.put(date,workingDay);
            calendar.add(Calendar.DATE,1);
        }
        for(WorkingDay workingDay:workingDays){
            calendar.setTime(workingDay.getWorkingDate());
            int workingDAyMouth = calendar.get(Calendar.MONTH);
            if(mouth == workingDAyMouth) {
                workingDayMap.put(workingDay.getWorkingDate(), workingDay);
            }
        }
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
        String jsonString = JSON.toJSONString(workingDayMap, SerializerFeature.WriteDateUseDateFormat);
        for (Map.Entry<Date,WorkingDay> entry : workingDayMap.entrySet()){
            Date key = entry.getKey();
            System.out.print(key);
            WorkingDay value = entry.getValue();
            System.out.println("-----" + value);
        }
        System.out.println(jsonString);
    }

    @Test
    public void getAll() throws Exception {
    }

}