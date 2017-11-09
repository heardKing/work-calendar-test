package com.calendar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WorkShift {

    @Id
    @GeneratedValue
    private Long workShiftID;//主键

    @Column(name = "ShiftName")
    private String shiftName;//班次名称

    @Column(name = "ShiftCode")
    private String shiftCode;//班次编码

    public Long getWorkShiftID() {
        return workShiftID;
    }

    public void setWorkShiftID(Long workShiftID) {
        this.workShiftID = workShiftID;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getShiftCode() {
        return shiftCode;
    }

    public void setShiftCode(String shiftCode) {
        this.shiftCode = shiftCode;
    }

    @Override
    public String toString() {
        return "WorkShift{" +
                "workShiftID=" + workShiftID +
                ", shiftName='" + shiftName + '\'' +
                ", shiftCode='" + shiftCode + '\'' +
                '}';
    }
}
