package com.swnur.tasktransactionapi.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableFeignClients(basePackages = "com.swnur.tasktransactionapi.proxy")
public class ApplicationConfig {
}
