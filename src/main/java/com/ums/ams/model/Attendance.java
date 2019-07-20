package com.ums.ams.model;

import java.util.Date;

public class Attendance {

  public Long id;
  public String employeeId;
  public Date checkTime;
  public String checkType;
  public int sensorId;
  public Long autoIncrement;

  public Attendance(){}

  public Attendance(Long id, String employeeId, Date checkTime, String checkType, int sensorId, Long autoIncrement) {
    this.id = id;
    this.employeeId = employeeId;
    this.checkTime = checkTime;
    this.checkType = checkType;
    this.sensorId = sensorId;
    this.autoIncrement = autoIncrement;
  }

  public Attendance(String employeeId, Date checkTime, String checkType, int sensorId, Long autoIncrement) {
    this.employeeId = employeeId;
    this.checkTime = checkTime;
    this.checkType = checkType;
    this.sensorId = sensorId;
    this.autoIncrement = autoIncrement;
  }

  @Override
  public String toString() {
    return "Attendance{" +
        "id=" + id +
        ", employeeId='" + employeeId + '\'' +
        ", checkTime=" + checkTime +
        ", checkType='" + checkType + '\'' +
        ", autoIncrement='" + autoIncrement + '\'' +
        ", sensorId=" + sensorId +
        '}';
  }
}