package com.xjtu.qgsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableScheduling
public class QgSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(QgSystemApplication.class, args);
    }

}
