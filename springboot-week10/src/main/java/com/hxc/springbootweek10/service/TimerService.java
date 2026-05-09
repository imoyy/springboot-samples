package com.hxc.springbootweek10.service;

import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class TimerService {

    private final Timer timer = new Timer();

    public void scheduleReminder(String message, long delayMillis) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("⏰ 提醒: " + message);
            }
        }, delayMillis);
        System.out.println("已设置提醒，" + (delayMillis / 1000) + " 秒后提醒: " + message);
    }

    public void startCountdown(long totalMillis) {
        Timer countdownTimer = new Timer();
        countdownTimer.scheduleAtFixedRate(new TimerTask() {
            long remaining = totalMillis;

            @Override
            public void run() {
                System.out.println("倒计时剩余: " + (remaining / 1000) + " 秒");
                remaining -= 1000;
                if (remaining < 0) {
                    System.out.println("倒计时结束！");
                    countdownTimer.cancel();
                }
            }
        }, 0, 1000);
    }
}
