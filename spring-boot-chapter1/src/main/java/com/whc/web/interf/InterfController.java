package com.whc.web.interf;

import com.whc.model.Student;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by whc on 2017/3/28.
 */
@RestController("/interf")
public class InterfController {

    @ApiOperation(value = "找鸭子", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "a", value = "aa", required = false, dataType = "String"),
            @ApiImplicitParam(name = "b", value = "bb", required = false, dataType = "Integer")
    })
    @PostMapping("/find")
    public Object find(String a, Integer b) {
        return new Student("张三", 20, 0);
    }
}