package ua.moses.microservices.customers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javacrumbs.jsonunit.core.Option;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.moses.microservices.customers.model.Customer;
import ua.moses.microservices.customers.service.CustomersService;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CustomersController.class)
public class CustomersControllerTest {
    @Value("${customers.endpoint.url}")
    private String customersEndpointUrl;

    @MockBean
    private CustomersService customersService;

    @Inject
    private MockMvc mockMvc;

    public static String convertToJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllCustomersTest() throws Exception {
        String ownerId = "owner";
        List<Customer> expectedList = Arrays.asList(new Customer(ownerId, "stock1"), new Customer(ownerId, "stock2"));
        when(customersService.getAllCustomers(ownerId)).thenReturn(expectedList);

        String result = mockMvc.perform(get(customersEndpointUrl + "/" + ownerId)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThatJson(result).when(Option.IGNORING_ARRAY_ORDER).isEqualTo(expectedList);
        verify(customersService).getAllCustomers(ownerId);
        verifyNoMoreInteractions(customersService);

    }

    @Test
    public void getCustomerByIdTest() throws Exception {
        String customerId = "5a981eaba3e33c120c2c67bf";
        String ownerId = "owner";
        Customer expected = new Customer(customerId, ownerId, "customer1", false);
        when(customersService.getCustomerById(ownerId, customerId)).thenReturn(expected);

        mockMvc.perform(get(customersEndpointUrl + "/" + ownerId+ "/" + customerId)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));
        verify(customersService).getCustomerById(ownerId, customerId);
        verifyNoMoreInteractions(customersService);

    }

    @Test
    public void insertCustomerTest() throws Exception {
        Customer expected = new Customer("owner", "customer1");
        when(customersService.insertCustomer(any(Customer.class))).thenReturn(expected);

        mockMvc.perform(post(customersEndpointUrl)
                .content(convertToJsonString(expected))
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));

        verify(customersService).insertCustomer(any(Customer.class));
        verifyNoMoreInteractions(customersService);
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        Customer expected = new Customer("owner", "customer1");
        when(customersService.deleteCustomer(any(Customer.class))).thenReturn(expected);

        mockMvc.perform(delete(customersEndpointUrl)
                .contentType(APPLICATION_JSON_UTF8)
                .content(convertToJsonString(expected)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));

        verify(customersService).deleteCustomer(any(Customer.class));
        verifyNoMoreInteractions(customersService);
    }

    @Test
    public void updateCustomerTest() throws Exception {
        Customer expected = new Customer("owner", "customer1");
        when(customersService.updateCustomer(any(Customer.class))).thenReturn(expected);

        mockMvc.perform(put(customersEndpointUrl)
                .contentType(APPLICATION_JSON_UTF8)
                .content(convertToJsonString(expected)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));

        verify(customersService).updateCustomer(any(Customer.class));
        verifyNoMoreInteractions(customersService);
    }

}
