package com.ums.ams.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utils {
  final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
  final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  final static DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

  final static DateFormat simpleDateFormat1= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

  public static String getCurrentDate() {
    LocalDateTime now = LocalDateTime.now();
    return dateFormatter.format(now);
  }

  public static String getCurrentDateTime() {
    LocalDateTime now = LocalDateTime.now();
    return dateTimeFormatter.format(now);
  }

  public static String formatDateTime(Date date) {
    return simpleDateFormat.format(date);
  }

  public static Date formatDateTime1(String date) {
    try {
      return simpleDateFormat1.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;

  }

  public static Date formatDateTime(String date) {
    try {
      return simpleDateFormat.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;

  }

}
