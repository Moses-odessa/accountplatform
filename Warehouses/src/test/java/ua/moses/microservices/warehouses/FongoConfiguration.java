package ua.moses.microservices.warehouses;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan(basePackages = "ua.moses.microservices.warehouses")
@EnableMongoRepositories(basePackages = "ua.moses.microservices.warehouses.repository")
@Configuration
public class FongoConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "warehouses-test";
    }

    @Bean
    @Override
    public Mongo mongo() {
        // uses fongo for in-memory tests
        return new Fongo("mongo-test").getMongo();
    }

}