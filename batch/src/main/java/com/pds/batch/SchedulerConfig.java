package com.pds.batch;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

public class SchedulerConfig {


    @Resource
    private MyJobFactory myJobFactory;


    @Bean
public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("dataSource")DataSource dataSource){
        SchedulerFactoryBean factory=new SchedulerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setJobFactory(myJobFactory);
        factory.setStartupDelay(2); //程序启动好2秒后开始执行quartz
        return factory;
    }


}
