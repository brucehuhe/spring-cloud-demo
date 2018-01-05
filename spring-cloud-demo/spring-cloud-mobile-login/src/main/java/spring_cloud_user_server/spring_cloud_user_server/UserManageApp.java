package spring_cloud_user_server.spring_cloud_user_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class UserManageApp 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(UserManageApp.class, args);
    }
}
