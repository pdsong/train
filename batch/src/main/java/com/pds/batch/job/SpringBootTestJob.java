package com.pds.batch.job;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
//@EnableScheduling
//public class SpringBootTestJob {
//
//    //cron表达式  秒 分 小时 月份中的日期 月份 星期中的日期 年份
//    //网上有勾选之后生成表达式
//    //自带的定时任务 只适合单体应用 不适合集群
//    // 解决： // 增加分布式锁
//    // 但是没法实时更改定时任务状态和策略
//    @Scheduled(cron = "0/5 * * * * ?")
//    private void test(){
//        System.out.println("spirngbootTestJOB test");
//    }
//}
