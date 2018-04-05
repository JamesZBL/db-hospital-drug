package me.zbl.common.listenner;

import me.zbl.common.quartz.utils.QuartzManager;
import me.zbl.common.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class ScheduleJobInitListener implements CommandLineRunner {

  @Autowired
  JobService scheduleJobService;

  @Autowired
  QuartzManager quartzManager;

  @Override
  public void run(String... arg0) {
    try {
      scheduleJobService.initSchedule();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}