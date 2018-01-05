package org.spring.cloud.order.service;

import org.spring.cloud.order.service.conf.TaskThreadPoolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableAsync  
@EnableConfigurationProperties({TaskThreadPoolConfig.class} ) // 开启异步配置属性支持  
public class OrderServiceApp 
{
    public static void main( String[] args )
    {
    	 SpringApplication.run(OrderServiceApp.class, args);
    }
}
