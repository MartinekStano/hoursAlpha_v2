package sk.posam.hoursalpha.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import sk.posam.hoursalpha.domain.service.IDayRecordService;

@Configuration
@EnableScheduling
public class ScheduleConfiguration {

    @Autowired
    private IDayRecordService dayRecordService;

    @Scheduled(cron = "0 0 13 * * SAT-SUN") //second minute hour day month weekday
    public void sendNotificationToEmployee(){
        dayRecordService.sendNotificationIfDayRecordDoesntExist();
    }
}
