package com.champsoft.Lab01_Restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CarDealershipWebAppV2Application {
    @Bean
    RestTemplate restTemplate() { return new RestTemplate();}
    public static void main(String[] args) {
        SpringApplication.run(CarDealershipWebAppV2Application.class, args);
    }

}
