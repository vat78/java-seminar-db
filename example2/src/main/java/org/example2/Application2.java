package org.example2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Application2 {
    public static void main(String[] args) {
        SpringApplication.run(Application2.class, args);
    }
}