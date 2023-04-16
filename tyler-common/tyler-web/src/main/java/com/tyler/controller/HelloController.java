package com.tyler.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tyler
 * @version 1.0.0
 * @date 4/15/2023 3:05 PM
 * @description TODO
 */
@Slf4j
@RestController
public class HelloController {

    @Value("${spring.datasource.url}")
    private String dburl;

    @Value("${spring.data.redis.port}")
    private Integer port;

    @Value("${a.b}")
    private String a;

    @GetMapping("/hello")
    public String hello(){
        System.out.println(dburl);
//        System.out.println(port);
        log.info("[hello] log4j testing");
        return dburl + " : " + a + " : " + port;
    }
}
