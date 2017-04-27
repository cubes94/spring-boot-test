package com.whc.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by whc on 2017/4/25.
 */
@Component
public class Task {

    public static Logger LOGGER = LoggerFactory.getLogger(Task.class);

    @Async
    public Future<String> task (int index) throws Exception {
        LOGGER.debug("task" + index + " beginning...");
        Long start = System.currentTimeMillis();
        Thread.sleep(new Random().nextInt(10000));
        Long end = System.currentTimeMillis();
        LOGGER.debug("task" + index + " ended, cost " + (end - start));
        return new AsyncResult<String>("task" + index + " done");
    }
}
