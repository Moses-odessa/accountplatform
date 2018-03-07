package ua.moses.microservices.customers.service;

import ua.moses.microservices.customers.model.Customer;

import java.util.List;

public interface CustomersService {

    List<Customer> getAllCustomers(String ownerId);

    Customer insertCustomer(Customer customer);

    Customer deleteCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer getCustomerById(String ownerId, String customerId);
}
