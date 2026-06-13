package com.ircsl.ircsl_tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IrcslTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(IrcslTrackingApplication.class, args);
    }

}






