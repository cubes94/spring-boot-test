package com.whc;

import com.whc.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Chapter5ApplicationTest {

    public static Logger LOGGER = LoggerFactory.getLogger(Chapter5ApplicationTest.class);

    @Autowired
    private Task task;

    @Test
    public void testAsyncTask() throws Exception {
        Long start = System.currentTimeMillis();
        List<Future<String>> futures = new ArrayList<Future<String>>();
        for (int i=0; i<10; i++) {
            futures.add(task.task(i));
        }
        while (true) {
            boolean allDone = true;
            for (Future<String> f : futures) {
                if (!f.isDone()) {
                    allDone = false;
                }
            }
            if (allDone) break;
            Thread.sleep(1000);
        }
        Long end = System.currentTimeMillis();
        LOGGER.info("all tasks done, cost " + (end - start));
    }
}
