package ua.moses.microservices.customers.repository;

import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.InMemoryMongoDb;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.moses.microservices.customers.model.Customer;

import java.util.List;

import static com.lordofthejars.nosqlunit.mongodb.InMemoryMongoDb.InMemoryMongoRuleBuilder.newInMemoryMongoDbRule;
import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomersRepositoryTest {
    @ClassRule
    public static InMemoryMongoDb inMemoryMongoDb = newInMemoryMongoDbRule().build();

    @Rule
    public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("customers-test");

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CustomersRepository customersRepository;

    @Test
    @UsingDataSet(locations = {"/customers.json"}, loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    public void FindAllCountTest() {
        List<Customer> result = customersRepository.getAllCustomers("owner");
        assertThat(result.size()).isEqualTo(2);
        result = customersRepository.findAll();
        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    @UsingDataSet(locations = {"/customers.json"}, loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    public void findByOwnerIdAndIdTest() {
        Customer result = customersRepository.findByOwnerIdAndId("owner", "5a99550ea3e33c0e8464140a");
        assertThat(result).isNotNull();
        result = customersRepository.findByOwnerIdAndId("owner2", "5a99550ea3e33c0e84641400");
        assertThat(result).isNull();
    }
}
