package com.pds.batch;

import jakarta.annotation.Resource;
import org.quartz.Trigger;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

@Component
public class MyJobFactory extends SpringBeanJobFactory {

    @Resource
    private AutowireCapableBeanFactory beanFactory;

    /*
    *  覆盖了super的createJobInstance方法，对其创建出来的类再进行autowire
     */

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle)throws  Exception{
        Object jobInstance=super.createJobInstance(bundle);
        beanFactory.autowireBean(jobInstance);
        return jobInstance;



    }
}
