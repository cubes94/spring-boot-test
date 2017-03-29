package com.whc.web;

import com.whc.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by whc on 2017/3/28.
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/hello/thymeleaf")
    public String helloT(ModelMap map) {
        map.addAttribute("student", new Student("张三", 20, 0));
        return "thymeleaf/index";
    }

    @GetMapping("/hello/freemarker")
    public String helloF(ModelMap map) {
        map.addAttribute("student", new Student("张三", 20, 0));
        return "freemarker/index";
    }
}
