package com.ums.ams.model;

import java.util.Date;

public class ZkTecoData {

  public String badgeNumber;
  public String checkTime;
  public Date checkDateTime;
  public int sensorId;
  public String checkType;
  public Long autoIncrement;

  @Override
  public String toString() {
    return "ZkTechoData{" +
        "badgeNumber='" + badgeNumber + '\'' +
        ", checkTime='" + checkTime + '\'' +
        ", checkDateTime=" + checkDateTime +
        ", sensorId=" + sensorId +
        ", checkType='" + checkType + '\'' +
        ", autoIncrement='" + autoIncrement + '\'' +
        '}';
  }
}
