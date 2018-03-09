package ua.moses.microservices.warehouses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class WarehousesApplication {
    public static void main(String[] args) {
        SpringApplication.run(WarehousesApplication.class, args);
    }
}
