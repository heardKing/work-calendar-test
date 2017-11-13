package com.calendar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class WorkingDay implements Comparable {

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
    @JsonIgnore
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

    public int compareTo(Object o) {
        System.out.println("compareTo...");
        WorkingDay workingDay = (WorkingDay)o;
        long anotherTime = workingDay.getWorkingDate().getTime();
        long thisTime = this.getWorkingDate().getTime();
        return (thisTime<anotherTime ? -1 : (thisTime==anotherTime ? 0 : 1));
    }

    /*
     * 为什么是31?
     * 1,31是一个质数,质数是能被1和自己本身整除的数
     * 2,31这个数既不大也不小
     * 3,31这个数好算,2的五次方-1,2向左移动5位
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.workingDate == null ? 0:this.workingDate.hashCode());
        System.out.println(result);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("equals...");
        if (this == obj)                        //调用的对象和传入的对象是同一个对象
            return true;                        //直接返回true
        if (obj == null)                        //传入的对象为null
            return false;                       //返回false
        if (getClass() != obj.getClass())       //判断两个对象对应的字节码文件是否是同一个字节码
            return false;                       //如果不是直接返回false
        WorkingDay workingDay = (WorkingDay) obj;
        long anotherTime = workingDay.getWorkingDate().getTime();
        long thisTime = this.getWorkingDate().getTime();
        return (anotherTime == thisTime);
    }
}
