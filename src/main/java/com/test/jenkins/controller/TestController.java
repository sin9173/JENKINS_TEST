package com.test.jenkins.controller;

import com.test.jenkins.utils.Test;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TestController {

    HashMap<String, Object> result = new HashMap<String, Object>();

    @PostMapping("/test1")
    public HashMap<String, Object> test1() {
        result.put("a", "1");
        result.put("b", "2");
        result.put("hello", Test.hello);

        return result;
    }
}
