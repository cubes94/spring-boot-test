package com.whc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by whc on 2017/3/28.
 */
@Controller
public class HelloController {

    public static Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/testLog")
    @ResponseBody
    public String testLogLevel() {
        LOGGER.debug("LOGGER LEVEL : DEBUG");
        LOGGER.info("LOGGER LEVEL : INFO");
        LOGGER.error("LOGGER LEVEL : ERROR");
        return "TEST FINISHED";
    }
}
