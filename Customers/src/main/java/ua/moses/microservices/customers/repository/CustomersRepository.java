package ua.moses.microservices.customers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ua.moses.microservices.customers.model.Customer;

import java.util.List;

public interface CustomersRepository extends MongoRepository<Customer, String> {
    @Query("{'$and':[{'ownerId': ?0},{'deleted': false}]}")
    List<Customer> getAllCustomers(String ownerId);

    Customer findByOwnerIdAndId(String ownerId, String customerId);

}
