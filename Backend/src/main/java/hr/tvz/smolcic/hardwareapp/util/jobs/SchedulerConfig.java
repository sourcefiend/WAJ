package hr.tvz.smolcic.hardwareapp.util.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail availableHardwareJobDetail() {
        return JobBuilder.newJob(AvailableHardwareJob.class).withIdentity("availableHardwareJob").storeDurably().build();
    }

    @Bean
    public Trigger availableHardwareJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();

        return TriggerBuilder.newTrigger().forJob(availableHardwareJobDetail()).withIdentity("availableHardwareTrigger").withSchedule(scheduleBuilder).build();
    }
}
