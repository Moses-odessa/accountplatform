package ua.moses.microservices.customers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.moses.microservices.customers.model.Customer;
import ua.moses.microservices.customers.service.CustomersService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "${customers.endpoint.url}", produces = "application/json;charset=UTF-8")
public class CustomersController {
    @Inject
    private CustomersService customersService;

    @GetMapping(value = "{ownerId}")
    public ResponseEntity<List<Customer>> getAllCustomers(@PathVariable String ownerId) {
        List<Customer> result = customersService.getAllCustomers(ownerId);
        if (result == null) {
            result = new ArrayList<>(0);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "{ownerId}/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String ownerId, @PathVariable String customerId) {
        Customer result = customersService.getCustomerById(ownerId, customerId);
        return ResponseEntity.ok(result);
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Customer> insertCustomer(@Valid @RequestBody Customer customer) {
        Customer result = customersService.insertCustomer(customer);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Customer> deleteCustomer(@Valid @RequestBody Customer customer) {
        Customer result = customersService.deleteCustomer(customer);
        return ResponseEntity.ok(result);
    }

    @PutMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) {
        Customer result = customersService.updateCustomer(customer);
        return ResponseEntity.ok(result);
    }

}
