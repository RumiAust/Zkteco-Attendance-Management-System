package com.ums.ams.rest;


import com.ums.ams.model.Attendance;
import com.ums.ams.repository.AttendanceRepository;
import com.ums.ams.service.AttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AttendanceRest {

  private static final Logger mLogger = LoggerFactory.getLogger(AttendanceRest.class);

  @Autowired
  AttendanceService attendanceService;

  @GetMapping("/sync/{id}")
  public List<Attendance> getAttendanceData(@PathVariable(value = "id") Long id) {
    mLogger.info("Invoked  getAttendanceData Rest Controller");
   return attendanceService.getData(id);
  }


}
