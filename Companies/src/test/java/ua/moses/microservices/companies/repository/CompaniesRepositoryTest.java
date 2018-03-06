package ua.moses.microservices.companies.repository;

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
import ua.moses.microservices.companies.model.Company;

import java.util.List;

import static com.lordofthejars.nosqlunit.mongodb.InMemoryMongoDb.InMemoryMongoRuleBuilder.newInMemoryMongoDbRule;
import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CompaniesRepositoryTest {
    @ClassRule
    public static InMemoryMongoDb inMemoryMongoDb = newInMemoryMongoDbRule().build();

    @Rule
    public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("companies-test");

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CompaniesRepository companiesRepository;

    @Test
    @UsingDataSet(locations = {"/companies.json"}, loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    public void FindAllCountTest() {
        List<Company> result = companiesRepository.getAllCompanies("owner");
        assertThat(result.size()).isEqualTo(2);
        result = companiesRepository.findAll();
        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    @UsingDataSet(locations = {"/companies.json"}, loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    public void findByOwnerIdAndIdTest() {
        Company result = companiesRepository.findByOwnerIdAndId("owner", "5a99550ea3e33c0e8464140a");
        assertThat(result).isNotNull();
        result = companiesRepository.findByOwnerIdAndId("owner2", "5a99550ea3e33c0e84641400");
        assertThat(result).isNull();
    }
}
