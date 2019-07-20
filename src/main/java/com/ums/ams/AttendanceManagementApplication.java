package com.ums.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Configuration
//@PropertySource(ignoreResourceNotFound = true,value = "${ams.application.properties}")
public class AttendanceManagementApplication {

  public static void main(String[] args) {
    System.out.println("In Main Method");
    SpringApplication.run(AttendanceManagementApplication.class, args);
  }

}
