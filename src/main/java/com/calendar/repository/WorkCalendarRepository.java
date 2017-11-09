package com.calendar.repository;

import com.calendar.model.WorkCalendar;

import java.util.List;

public interface WorkCalendarRepository {

    /**
     * 保存工作日历到数据库，并返回保存成功后的工作日历
     * @param workCalendar
     * @return
     */
    WorkCalendar save(WorkCalendar workCalendar);

    /**
     * 修改工作日历，并返回修改后的工作日历
     * @param workCalendar
     * @return
     */
    WorkCalendar update(WorkCalendar workCalendar);

    /**
     * 删除对应ID的工作日历
     * @param id
     */
    void delete(long id);

    /**
     * 查询一个
     * @param id
     * @return
     */
    WorkCalendar getByID(long id);

    /**
     * 查询所有
     * @return
     */
    List<WorkCalendar> getAll();

}
