package ua.moses.microservices.warehouses.repository;

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
import ua.moses.microservices.warehouses.model.Warehouse;

import java.util.List;

import static com.lordofthejars.nosqlunit.mongodb.InMemoryMongoDb.InMemoryMongoRuleBuilder.newInMemoryMongoDbRule;
import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WarehousesRepositoryTest {
    @ClassRule
    public static InMemoryMongoDb inMemoryMongoDb = newInMemoryMongoDbRule().build();

    @Rule
    public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("warehouses-test");

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WarehousesRepository warehousesRepository;

    @Test
    @UsingDataSet(locations = {"/warehouses.json"}, loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    public void testCountAllWarehouses() {
        List<Warehouse> result = this.warehousesRepository.getAllWarehouses("owner");
        assertThat(result.size()).isEqualTo(2);
        result = this.warehousesRepository.findAll();
        assertThat(result.size()).isEqualTo(4);
    }
}
