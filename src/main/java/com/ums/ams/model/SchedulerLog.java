package com.ums.ams.model;

import java.util.Date;

public class SchedulerLog {

  public Long id;
  public Long syncedUpTo;

  public SchedulerLog() {}
  public SchedulerLog(Long id, Long syncedUpTo) {
    this.id = id;
    this.syncedUpTo = syncedUpTo;
  }

  public SchedulerLog(Long endDateTime) {
    this.syncedUpTo = endDateTime;
  }

  @Override
  public String toString() {
    return "SchedulerLog{" +
        "id=" + id +
        ", syncedUpTo=" + syncedUpTo +
        '}';
  }
}
