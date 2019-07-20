package com.ums.ams.service;

import com.ums.ams.model.SchedulerLog;
import com.ums.ams.repository.SchedulerLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SchedulerLogService {
  private static final Logger mLogger = LoggerFactory.getLogger(SchedulerLogService.class);

  @Autowired
  SchedulerLogRepository schedulerLogRepository;

  public void insertLog(Long lastIndex) {
    schedulerLogRepository.insertLog(lastIndex);
  }

  public Long getLastId() {
    SchedulerLog log = schedulerLogRepository.getLastExecutionLog();
    return log.syncedUpTo;
  }

}
