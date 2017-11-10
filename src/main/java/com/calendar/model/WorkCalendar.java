package com.calendar.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class WorkCalendar {

    @Id
    @GeneratedValue
    private Long calendarID;//日历ID

    private String calendarCode;//日历代码

    private String calendarName;//日历名称

    @ManyToOne
    @JoinColumn(name = "calendarTemplateID")
//    @Fetch(FetchMode.SELECT)
    private WorkCalendar calendarTemplate;//模板工作日历ID

    @Temporal(TemporalType.DATE)
    private Date startDate;//工作日历其实日期

    @Temporal(TemporalType.DATE)
    private Date endDate;//工作日历结束日期

    @OneToOne
    @JoinColumn(name = "workShiftID")
//    @Fetch(FetchMode.SELECT)
    private WorkShift workShift;//默认班次

    private String RestDayRule;//休息日规则1~7分别代表周一到周日使用逗号分隔

    private String isAdministration;//是否作用于行政   0：否；1：是；

    private String isProduction;//是否用于生产   0：否；1：是；

    private String isEquipment;//是否用于设备   0：否；1：是；

    private String isTemplate;//是否是模板   0：否；1：是；

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "workCalendarID")
    @Fetch(FetchMode.SUBSELECT)
    private List<WorkingDay> workingDays = new ArrayList<WorkingDay>();//

    public Long getCalendarID() {
        return calendarID;
    }

    public void setCalendarID(Long calendarID) {
        this.calendarID = calendarID;
    }

    public String getCalendarCode() {
        return calendarCode;
    }

    public void setCalendarCode(String calendarCode) {
        this.calendarCode = calendarCode;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public WorkCalendar getCalendarTemplate() {
        return calendarTemplate;
    }

    public void setCalendarTemplate(WorkCalendar calendarTemplate) {
        this.calendarTemplate = calendarTemplate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public WorkShift getWorkShift() {
        return workShift;
    }

    public void setWorkShift(WorkShift workShift) {
        this.workShift = workShift;
    }

    public String getRestDayRule() {
        return RestDayRule;
    }

    public void setRestDayRule(String restDayRule) {
        RestDayRule = restDayRule;
    }

    public String getIsAdministration() {
        return isAdministration;
    }

    public void setIsAdministration(String isAdministration) {
        this.isAdministration = isAdministration;
    }

    public String getIsProduction() {
        return isProduction;
    }

    public void setIsProduction(String isProduction) {
        this.isProduction = isProduction;
    }

    public String getIsEquipment() {
        return isEquipment;
    }

    public void setIsEquipment(String isEquipment) {
        this.isEquipment = isEquipment;
    }

    public String getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(String isTemplate) {
        this.isTemplate = isTemplate;
    }

    public List<WorkingDay> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(List<WorkingDay> workingDays) {
        this.workingDays = workingDays;
    }

    @Override
    public String toString() {
        return "WorkCalendar{" +
                "calendarID=" + calendarID +
                ", calendarCode='" + calendarCode + '\'' +
                ", calendarName='" + calendarName + '\'' +
                ", calendarTemplate=" + calendarTemplate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", workShift=" + workShift +
                ", RestDayRule='" + RestDayRule + '\'' +
                ", isAdministration='" + isAdministration + '\'' +
                ", isProduction='" + isProduction + '\'' +
                ", isEquipment='" + isEquipment + '\'' +
                ", isTemplate='" + isTemplate + '\'' +
                ", workingDays=" + workingDays +
                '}';
    }
}
