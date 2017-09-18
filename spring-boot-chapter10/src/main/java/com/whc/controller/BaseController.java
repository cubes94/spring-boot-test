package com.whc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by whc on 2017/9/18.
 */
@RestController
@RequestMapping("/base")
public class BaseController {

    @Autowired
    @Qualifier("githubProcessor")
    private PageProcessor githubProcessor;

    @PostMapping("/crawler/github")
    public Object githubCrawler() {
        Spider.create(githubProcessor).addUrl("https://github.com/cubes94").thread(5).run();
        return 0;
    }
}
