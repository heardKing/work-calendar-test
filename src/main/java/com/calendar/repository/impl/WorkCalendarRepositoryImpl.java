package com.calendar.repository.impl;

import com.calendar.model.WorkCalendar;
import com.calendar.repository.WorkCalendarRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class WorkCalendarRepositoryImpl implements WorkCalendarRepository {

    private Session session;

    public WorkCalendarRepositoryImpl(Session session){
        this.session = session;
    }

    public WorkCalendar save(WorkCalendar workCalendar) {
//        Transaction transaction = this.session.beginTransaction();
//        transaction.begin();
        this.session.save(workCalendar);
//        transaction.commit();
        return workCalendar;
    }

    public WorkCalendar update(WorkCalendar workCalendar) {
        Transaction transaction = this.session.beginTransaction();
        transaction.begin();
        this.session.update(workCalendar);
        transaction.commit();
        return workCalendar;
    }

    public void delete(long id) {
        Transaction transaction = this.session.beginTransaction();
        transaction.begin();
        this.session.delete(this.getByID(id));
        transaction.commit();
    }

    public WorkCalendar getByID(long id) {
        return (WorkCalendar) this.session.get(WorkCalendar.class,id);
    }

    public List<WorkCalendar> getAll() {
        List<WorkCalendar> list = this.session.createCriteria(WorkCalendar.class).list();
        return list;
    }
}
