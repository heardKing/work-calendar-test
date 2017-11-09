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

    @OneToOne
    @JoinColumn(name = "workShiftID")
    private WorkShift workShift;//工作日对应的班次

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

    public WorkShift getWorkShift() {
        return workShift;
    }

    public void setWorkShift(WorkShift workShift) {
        this.workShift = workShift;
    }

    @Override
    public String toString() {
        return "WorkingDay{" +
                "workingDayID=" + workingDayID +
                ", workingDate=" + workingDate +
                ", workShift=" + workShift +
                '}';
    }
}
