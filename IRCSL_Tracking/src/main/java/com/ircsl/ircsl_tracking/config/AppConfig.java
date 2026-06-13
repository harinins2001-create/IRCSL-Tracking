package com.ircsl.ircsl_tracking.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class AppConfig {

    // 1. MongoDB Connection එක කෙලින්ම Code එකෙන් සම්බන්ධ කිරීම
    @Bean
    public MongoClient mongoClient() {
        String atlasUri = "mongodb+srv://ircsldb:ircsldb@ircsldb.hvc95no.mongodb.net/?appName=ircsldb";
        return MongoClients.create(atlasUri);
    }

    @Bean
    public MongoTemplate mongoTemplate() {

        return new MongoTemplate(mongoClient(), "ircsl_db");
    }


    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("harinins2001@gmail.com");
        mailSender.setPassword("xlfv hwzb nsnp dntx"); // Google App Password එක

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", "smtp");

        return mailSender;
    }
}