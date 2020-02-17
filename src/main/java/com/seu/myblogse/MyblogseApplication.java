package com.seu.myblogse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.seu.myblogse.mapper"})
public class MyblogseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyblogseApplication.class, args);
    }

}
