package com.spt.springboot.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyJob implements Job {

    // 使用日志
    private Logger log = LoggerFactory.getLogger(MyJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 定时任务
        log.info(sdf.format(new Date()) + "-->" + "Hello Spring Boot Quartz");
    }
}
