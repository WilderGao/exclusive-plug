package com.qg.exclusiveplug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Wilder
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class ExclusivePlugApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExclusivePlugApplication.class, args);
    }
}
