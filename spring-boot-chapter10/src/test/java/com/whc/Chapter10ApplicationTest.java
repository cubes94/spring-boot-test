package com.whc;

import com.whc.dao.BaseLogMongoRepository;
import com.whc.processor.GithubProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import us.codecraft.webmagic.Spider;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Chapter10ApplicationTest {

    private static Logger LOGGER = LoggerFactory.getLogger(Chapter10ApplicationTest.class);

    @Autowired
    private BaseLogMongoRepository baseLogMongoRepository;

    @Test
    public void testGithub() {
        baseLogMongoRepository.deleteAll();
    }
}
