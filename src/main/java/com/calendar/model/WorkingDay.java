package com.calendar.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class WorkingDay {

    @Id
    @GeneratedValue
    private Long workingDayID;

    @Temporal(TemporalType.DATE)
    private Date workingDate;//工作日的日期

    private String dateType;//日期类型，工作日or休息日

    @OneToOne
    @JoinColumn(name = "workShiftID")
    private WorkShift workShift;//工作日对应的班次

    @ManyToOne
    @JoinColumn(name = "workCalendarID")
    private WorkCalendar workCalendar;//工作日对应的工作日历

    public Long getWorkingDayID() {
        return workingDayID;
    }

    public void setWorkingDayID(Long workingDayID) {
        this.workingDayID = workingDayID;
    }

    public Date getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(Date workingDate) {
        this.workingDate = workingDate;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public WorkShift getWorkShift() {
        return workShift;
    }

    public void setWorkShift(WorkShift workShift) {
        this.workShift = workShift;
    }

    public WorkCalendar getWorkCalendar() {
        return workCalendar;
    }

    public void setWorkCalendar(WorkCalendar workCalendar) {
        this.workCalendar = workCalendar;
    }

    @Override
    public String toString() {
        return "WorkingDay{" +
                "workingDayID=" + workingDayID +
                ", workingDate=" + workingDate +
                ", dateType='" + dateType + '\'' +
                ", workShift=" + workShift +
                '}';
    }
}
