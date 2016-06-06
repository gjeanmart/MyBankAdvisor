package com.gjeanmart.mybankadvisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration
//@ImportResource({"classpath*:spring/spring-config.xml"})
public class MyBankAdvisor {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MyBankAdvisor.class, args);
    }
}
