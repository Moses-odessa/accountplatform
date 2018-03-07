package ua.moses.microservices.customers.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.moses.microservices.customers.model.Customer;
import ua.moses.microservices.customers.repository.CustomersRepository;
import ua.moses.microservices.customers.service.impl.CustomersServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomersServiceTest {

    private CustomersService customersService;

    @MockBean
    private CustomersRepository customersRepository;

    @Before
    public void init() {
        customersService = new CustomersServiceImpl(customersRepository);
    }

    @Test
    public void getAllCustomersTest() {
        String ownerId = "owner";
        List<Customer> expectedList = Arrays.asList(new Customer(ownerId,"customer1"), new Customer(ownerId, "customer2"));
        when(customersRepository.getAllCustomers(ownerId)).thenReturn(expectedList);
        List<Customer> result = customersService.getAllCustomers(ownerId);
        assertEquals(expectedList, result);
    }

    @Test
    public void getCustomerByIdTest() {
        String customerId = "5a981eaba3e33c120c2c67bf";
        String ownerId = "owner";
        Customer expected = new Customer(customerId, ownerId, "customer1", false);
        when(customersRepository.findByOwnerIdAndId(ownerId, customerId)).thenReturn(expected);
        Customer result = customersService.getCustomerById(ownerId, customerId);
        assertEquals(expected, result);
    }

    @Test
    public void insertCustomerTest() {
        Customer expected = new Customer("owner","customer1");
        when(customersRepository.insert(expected)).thenReturn(expected);
        Customer result = customersService.insertCustomer(expected);
        assertEquals(expected, result);
    }


    @Test
    public void deleteCustomerTest() {
        Customer given = new Customer("owner","customer1");
        when(customersRepository.exists(given.getId())).thenReturn(true);
        Customer result = customersService.deleteCustomer(given);
        assertEquals(true, result.isDeleted());
        verify(customersRepository).save(given);
    }

    @Test
    public void updateCustomerTest() {
        Customer expected = new Customer("owner","customer1");
        expected.setDeleted(true);
        when(customersRepository.exists(expected.getId())).thenReturn(true);
        when(customersRepository.save(expected)).thenReturn(expected);
        Customer result = customersService.updateCustomer(expected);
        assertEquals(expected, result);
    }

}
