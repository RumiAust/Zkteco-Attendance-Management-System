package com.ums.ams.repository;

import com.ums.ams.model.SchedulerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class SchedulerLogRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void insertLog(Long lastIndex) {
    jdbcTemplate.update("INSERT INTO scheduler_log(SYNCED_UPTO, INSERTED_ON) VALUES (?, now())", lastIndex);
  }

  public SchedulerLog getLastExecutionLog() {
    String sql = "SELECT   *  FROM scheduler_log where ID=( select max(ID) from scheduler_log ) ";

    SchedulerLog log = (SchedulerLog) jdbcTemplate.queryForObject(
        sql,
        new SchedulerLogRowMapper());

    return log;
  }


  class SchedulerLogRowMapper implements RowMapper<SchedulerLog> {
    @Override
    public SchedulerLog mapRow(ResultSet resultSet, int i) throws SQLException {
      SchedulerLog log = new SchedulerLog();
      log.id = resultSet.getLong("ID");
      log.syncedUpTo = resultSet.getLong("SYNCED_UPTO");
      AtomicReference<SchedulerLog> atomicReference = new AtomicReference<>(log);
      return atomicReference.get();
    }
  }

}
