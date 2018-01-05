package org.spring.cloud.sleuth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import zipkin.server.EnableZipkinServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication  
@EnableZipkinServer  
@EnableEurekaClient
public class SleuthServerApplication {  
  
      
    public static void main(String[] args) {  
        SpringApplication.run(SleuthServerApplication.class,args);  
    }  
}  