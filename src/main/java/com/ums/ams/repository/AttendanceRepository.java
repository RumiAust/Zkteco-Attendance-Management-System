package com.ums.ams.repository;

import com.ums.ams.model.Attendance;
import com.ums.ams.model.SchedulerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class AttendanceRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void insertAttendance(String employeeId, Date checkTime, String checkType, int sensorId) {

    jdbcTemplate.update("INSERT INTO attendance_info(EMPLOYEE_ID, CHECK_TIME, CHECK_TYPE, SENSOR_ID) VALUES (?,?,?,?)",
        employeeId, checkTime, checkType, sensorId);

  }

  public void insertBatchAttendance(List<Attendance> attendanceList) {
    String sql = "INSERT INTO attendance_info(EMPLOYEE_ID, CHECK_TIME, CHECK_TYPE, SENSOR_ID,AUTO_INCREMENT) VALUES (?,?,?,?,?)";
    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

      @Override
      public void setValues(PreparedStatement ps, int i) throws SQLException {
        Attendance attendance = attendanceList.get(i);
        ps.setString(1, attendance.employeeId);
        ps.setTimestamp(2, new Timestamp(attendance.checkTime.getTime()));
        ps.setString(3, attendance.checkType);
        ps.setInt(4, attendance.sensorId);
        ps.setLong(5, attendance.autoIncrement);
      }

      @Override
      public int getBatchSize() {
        return attendanceList.size();
      }

    });
  }

  public List<Attendance> getData(Long id) {
    String sql = "SELECT   *  FROM attendance_info where  AUTO_INCREMENT>?";

    return jdbcTemplate.query(
        sql, new Object[]{id},
        new AttendanceRepository.AttendanceRowMapper());
  }


  class AttendanceRowMapper implements RowMapper<Attendance> {
    @Override
    public Attendance mapRow(ResultSet resultSet, int i) throws SQLException {
      Attendance attendance = new Attendance();
      attendance.id = resultSet.getLong("ID");
      attendance.employeeId = resultSet.getString("EMPLOYEE_ID");
      attendance.checkTime = resultSet.getTimestamp("CHECK_TIME");
      attendance.checkType = resultSet.getString("CHECK_TYPE");
      attendance.sensorId = resultSet.getInt("SENSOR_ID");
      attendance.autoIncrement = resultSet.getLong("AUTO_INCREMENT");
      AtomicReference<Attendance> atomicReference = new AtomicReference<>(attendance);
      return atomicReference.get();
    }
  }


}
