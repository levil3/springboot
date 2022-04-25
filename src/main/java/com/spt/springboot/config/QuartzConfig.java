package com.spt.springboot.config;

import com.spt.springboot.jobs.MyJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  构建Quartz触发配置类
 *
 */
@Configuration
public class QuartzConfig {

    // 准备JobDetail
    @Bean
    public JobDetail jobDetail01() {
        return JobBuilder.newJob(MyJob.class).storeDurably().build();
    }

    // 准备Trigger
//    @Bean
//    public Trigger trigger01() {
//        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                // 每一秒执行一次
//                .withIntervalInSeconds(1)
//                // 永久重复，一直执行下去
//                .repeatForever();
//        return TriggerBuilder.newTrigger()
//                .withIdentity("trigger1","group1")
//                .withSchedule(simpleScheduleBuilder)
//                // 指定给哪个jobDetail
//                .forJob(jobDetail01())
//                .build();
//    }

    /**
     *  利用表达式形式实现Trigger
     */
    @Bean
    public Trigger trigger02() {
        return TriggerBuilder.newTrigger()
                .withIdentity("trigger2","group1")
                // 每隔5秒执行一次
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? *"))
                .forJob(jobDetail01())
                .build();
    }

}
