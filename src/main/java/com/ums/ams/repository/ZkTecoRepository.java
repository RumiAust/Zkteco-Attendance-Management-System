package com.ums.ams.repository;

import com.ums.ams.model.ZkTecoData;
import com.ums.ams.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ZkTecoRepository {

  @Value("${zkteco.file.location}")
  private String dbLocation;

  public List<ZkTecoData> getData(Long id) {

    List<ZkTecoData> zkTecoList = new ArrayList();
    try (Connection connection = DriverManager.getConnection(dbLocation)) {

      String sql =
          " SELECT userInfo.USERID, userInfo.Badgenumber BADGE_NUMBER, userInfo.Name,  " +
              "to_char(checkInOut.CHECKTIME ,  'dd/MM/yyyy hh:mi:ss AM')  CHECK_TIME, " +
              " checkInOut.CHECKTYPE CHECK_TYPE, checkInOut.VERIFYCODE, checkInOut.SENSORID  SENSOR_ID, checkInOut.Auto_increment" +
              " FROM  [CHECKINOUT] checkInOut, [USERINFO] userInfo where checkInOut.USERID=userInfo.USERID  " +
              " AND checkInOut.Auto_increment>"+id +" order by checkInOut.Auto_increment";

      Statement statement = connection.createStatement();
      ResultSet result = statement.executeQuery(sql);

      while (result.next()) {
        ZkTecoData data = new ZkTecoData();
        data.badgeNumber = result.getString("BADGE_NUMBER");
        data.sensorId = result.getInt("SENSOR_ID");
        data.checkTime = result.getString("CHECK_TIME");
        data.checkDateTime = Utils.formatDateTime1(data.checkTime);
        data.checkType = result.getString("CHECK_TYPE");
        data.autoIncrement = result.getLong("Auto_increment");
        zkTecoList.add(data);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return zkTecoList;
  }

}
