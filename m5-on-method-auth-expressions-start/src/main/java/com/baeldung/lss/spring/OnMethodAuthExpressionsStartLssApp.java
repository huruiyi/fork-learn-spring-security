package com.baeldung.lss.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.baeldung.lss.web")
@EnableJpaRepositories("com.baeldung.lss")
@EntityScan("com.baeldung.lss.web.model")
public class OnMethodAuthExpressionsStartLssApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Class[]{OnMethodAuthExpressionsStartLssApp.class, LssSecurityConfig.class, LssWebMvcConfiguration.class}, args);
    }

}
