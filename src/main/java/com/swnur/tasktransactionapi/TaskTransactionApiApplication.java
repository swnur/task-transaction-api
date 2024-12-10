package com.swnur.tasktransactionapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.swnur.tasktransactionapi.proxy")
public class TaskTransactionApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskTransactionApiApplication.class, args);
    }

}
