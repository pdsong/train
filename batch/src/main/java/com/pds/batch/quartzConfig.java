package com.pds.batch;

import com.pds.batch.job.TestJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class quartzConfig {

    /**
     * 声明一个任务
     */
    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(TestJob.class)
                .withIdentity("TestJob","testGroup")
                .storeDurably()
                .build();
    }

    /**
     * 声明一个触发器
     */
    @Bean
    public Trigger trigger(){
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("trigger","triggerGroup")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?"))
                .build();
    }
}
