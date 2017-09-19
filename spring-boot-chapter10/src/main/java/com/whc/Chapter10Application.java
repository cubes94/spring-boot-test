package com.whc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import us.codecraft.webmagic.processor.PageProcessor;

@SpringBootApplication
public class Chapter10Application implements CommandLineRunner {

    Logger LOGGER = LoggerFactory.getLogger(Chapter10Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Chapter10Application.class, args);
    }

    @Autowired
    @Qualifier("githubProcessor")
    private PageProcessor githubProcessor;

    public void run(String... strings) throws Exception {
        LOGGER.info("application is started ......");
//        Spider.create(githubProcessor).addUrl("https://github.com/cubes94").thread(5).run();
    }
}
