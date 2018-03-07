package ua.moses.microservices.customers.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ua.moses.microservices.customers.model.Customer;
import ua.moses.microservices.customers.repository.CustomersRepository;
import ua.moses.microservices.customers.service.CustomersService;

import javax.inject.Inject;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomersServiceImpl implements CustomersService {
    @Inject
    private CustomersRepository customersRepository;

    @Override
    public List<Customer> getAllCustomers(String ownerId) {
        return customersRepository.getAllCustomers(ownerId);
    }

    @Override
    public Customer insertCustomer(Customer customer) {
        return customersRepository.insert(customer);
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        if (customersRepository.exists(customer.getId())) {
            customer.setDeleted(true);
            customersRepository.save(customer);
            return customer;
        } else {
            return null;
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if (customersRepository.exists(customer.getId())) {
            return customersRepository.save(customer);
        } else {
            return null;
        }
    }

    @Override
    public Customer getCustomerById(String ownerId, String customerId) {
        return customersRepository.findByOwnerIdAndId(ownerId, customerId);
    }
}
