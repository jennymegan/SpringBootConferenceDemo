package com.pluralsight.demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class HomeController {

    @Value("${app.version}")
    private String appVersion;
//    this looks in the application.properties file for the app.version

    @GetMapping
    @RequestMapping("/")
    //now if you send a get to localhost/  - ie, the root - this is what you get back.
    public Map getStatus(){
        Map map = new HashMap<String, String>();
        map.put("app-version", appVersion);
        // this makes a hashmap with title of arg1, value of arg2
        return map;
    }

}


