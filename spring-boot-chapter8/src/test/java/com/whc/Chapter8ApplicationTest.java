package com.whc;

import com.whc.message.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Chapter8ApplicationTest {

    public static Logger LOGGER = LoggerFactory.getLogger(Chapter8ApplicationTest.class);

    @Autowired
    private Sender sender;

    @Test
    public void testRabbitMQ() {
        sender.send();
    }

}
