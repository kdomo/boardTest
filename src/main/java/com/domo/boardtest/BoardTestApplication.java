package com.domo.boardtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession

public class BoardTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardTestApplication.class, args);
    }

}
