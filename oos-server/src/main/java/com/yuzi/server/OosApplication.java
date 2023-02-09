package com.yuzi.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 星涯
 */
@SpringBootApplication
@MapperScan("com.yuzi.server.mapper")
public class OosApplication {
    public static void main(String[] args) {
        SpringApplication.run(OosApplication.class, args);
    }
}
