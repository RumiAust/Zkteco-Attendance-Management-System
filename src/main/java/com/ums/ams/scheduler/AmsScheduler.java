package com.ums.ams.scheduler;

import com.ums.ams.model.Attendance;
import com.ums.ams.model.SchedulerLog;
import com.ums.ams.model.ZkTecoData;
import com.ums.ams.service.AttendanceService;
import com.ums.ams.service.SchedulerLogService;
import com.ums.ams.service.ZkTecoService;
import com.ums.ams.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Component
public class AmsScheduler {

  private static final Logger mLogger = LoggerFactory.getLogger(AmsScheduler.class);
  @Autowired
  AttendanceService attendanceService;
  @Autowired
  SchedulerLogService schedulerLogService;
  @Autowired
  ZkTecoService zkTecoService;

  @Scheduled(cron = "${scheduler.configurationLoadRate}")
  public void fetchAttendanceInfo() throws SQLException {
    mLogger.info("Scheduler started at " + new Date());

    Long lastId = schedulerLogService.getLastId();
    List<ZkTecoData> syncDataList= zkTecoService.getData(lastId);
    List<Attendance> attendanceList = zkTecoService.convertData(syncDataList);

    attendanceService.insertBatchAttendance(attendanceList);

    if(attendanceList.size()>0)
    {
      Attendance attendance = attendanceList.get(attendanceList.size()-1);
      schedulerLogService.insertLog(attendance.autoIncrement);
    }


  }

}
