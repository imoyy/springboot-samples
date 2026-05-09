package com.hxc.springbootweek10.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ScheduledTaskService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedDelay = 5000)
    public void taskFixedDelay() {
        System.out.println("[fixedDelay] 任务执行: " + LocalDateTime.now().format(FORMATTER)
                + " (上次执行结束后等待 5 秒)");
    }

    @Scheduled(fixedRate = 6000)
    public void taskFixedRate() {
        System.out.println("[fixedRate] 任务执行: " + LocalDateTime.now().format(FORMATTER)
                + " (每 6 秒执行一次，不考虑上次是否完成)");
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void taskCron() {
        System.out.println("[cron] 任务执行: " + LocalDateTime.now().format(FORMATTER)
                + " (每分钟整点执行)");
    }
}
