package com.whc.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by whc on 2017/4/25.
 */
@Component
public class ScheduledTask {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void consoleCurrentTime() {
        System.out.println("now:  " + DATE_FORMAT.format(new Date()));
    }
}