package com.ums.ams.service;

import com.ums.ams.model.Attendance;
import com.ums.ams.model.ZkTecoData;
import com.ums.ams.repository.ZkTecoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ZkTecoService {

  private static final Logger mLogger = LoggerFactory.getLogger(ZkTecoService.class);

  @Autowired
  ZkTecoRepository zkTecoRepository;

  public List<ZkTecoData> getData(Long id) {
    return zkTecoRepository.getData(id);
  }

  public List<Attendance> convertData(List<ZkTecoData> dataList) {
    List<Attendance> attendanceList = new ArrayList();

    for(ZkTecoData data : dataList) {
      Attendance attendance = new Attendance(data.badgeNumber, data.checkDateTime, data.checkType, data.sensorId, data.autoIncrement);
      attendanceList.add(attendance);
    }

    return attendanceList;
  }

}
