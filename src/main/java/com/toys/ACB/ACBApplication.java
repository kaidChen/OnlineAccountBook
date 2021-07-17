package com.toys.ACB;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.toys.ACB.mapper")
public class ACBApplication {
    public static void main(String[] args) {
        SpringApplication.run(ACBApplication.class, args);
    }
}
