package com.qg.exclusiveplug.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WilderGao
 * time 2018-09-25 16:28
 * motto : everything is no in vain
 * description
 */
@RestController
public class TestController {
    @RequestMapping("/hello")
    public String hello(){
        return "test success!";
    }
}
