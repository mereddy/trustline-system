package com.ripple.trustline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = "com.ripple.trustline.*")
public class TrustlineApplication {

    private static Logger logger= LoggerFactory.getLogger(TrustlineApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TrustlineApplication.class, args);
        logger.info("Welcome to Trustline");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
