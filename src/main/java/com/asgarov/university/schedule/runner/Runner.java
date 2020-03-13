package com.asgarov.university.schedule.runner;

import com.asgarov.university.schedule.config.JDBCConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JDBCConfig.class);
    }
}
