package com.calendar.repository.impl;

import com.calendar.model.WorkCalendar;
import com.calendar.model.WorkShift;
import com.calendar.model.WorkingDay;
import com.calendar.repository.WorkCalendarRepository;
import com.calendar.utils.DateUtils;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void save() throws Exception {
        WorkCalendar workCalendar = new WorkCalendar();
        workCalendar.setCalendarCode("000-189");
        workCalendar.setCalendarName("工作日历测试名称");
        workCalendar.setCalendarTemplate(null);
//        workCalendar.setStartDate(DateUtils.getDate("2017-01-01"));
//        workCalendar.setEndDate(DateUtils.getDate("2017-12-31"));
/*-------------------保存班次-------------------------*/
        WorkShift workShift = new WorkShift();
        workShift.setWorkShiftID(1l);
        workShift.setShiftCode("shift-0001");
        workShift.setShiftName("测试用班次");
//        this.session.save(workShift);
        System.out.println(workShift);
        workCalendar.setWorkShift(workShift);
/*----------------------------------------------------*/
        workCalendar.setRestDayRule("1,3,5");
        workCalendar.setIsAdministration("1");
        workCalendar.setIsProduction("0");
        workCalendar.setIsEquipment("0");
        workCalendar.setIsTemplate("1");

        List<WorkingDay> workingDays = new ArrayList<WorkingDay>();
        WorkShift workShift2 = new WorkShift();
        workShift2.setWorkShiftID(2l);
        workShift2.setShiftCode("shift-0002");
        workShift2.setShiftName("测试用班次(自定义工作日)");
//        this.session.save(workShift2);
        for (int i = 0; i < 3; i++) {
            WorkingDay workingDay = new WorkingDay();
//            workingDay.setWorkingDate(DateUtils.getDate("2017-12-"+(i+2)));
            workingDay.setWorkShift(workShift2);
            workingDays.add(workingDay);
        }
        workCalendar.setWorkingDays(workingDays);
        Serializable save = this.session.save(workCalendar);
        System.out.println(save);
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void getByID() throws Exception {
        WorkCalendar workCalendar = (WorkCalendar)this.session.get(WorkCalendar.class, 1l);
//        System.out.println(workCalendar);
    }

    @Test
    public void getAll() throws Exception {
    }

}