package com.ums.ams.service;

import com.ums.ams.model.Attendance;
import com.ums.ams.repository.AttendanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AttendanceService {

  private static final Logger mLogger = LoggerFactory.getLogger(AttendanceService.class);

  @Autowired
  AttendanceRepository attendanceRepository;

  public void insertAttendance(String employeeId, Date checkTime, String checkType, int sensorId) {
    attendanceRepository.insertAttendance(employeeId, checkTime, checkType, sensorId);
  }

  public List<Attendance> getData(Long id) {
    return attendanceRepository.getData(id);
  }

  public void insertBatchAttendance(List attendanceList) {
    mLogger.info("Start of the for loop " + new Date());
    mLogger.info("Data size :" + attendanceList.size());
    mLogger.info("End of the for loop" + new Date());
    attendanceRepository.insertBatchAttendance(attendanceList);
    mLogger.info("Insert batch" + new Date());
  }
}
