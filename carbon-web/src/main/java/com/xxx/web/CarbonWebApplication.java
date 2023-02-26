package com.xxx.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
@ComponentScan({"com.xxx"})
@MapperScan({"com.xxx.mapper"})
public class CarbonWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarbonWebApplication.class, args);
    }

}
