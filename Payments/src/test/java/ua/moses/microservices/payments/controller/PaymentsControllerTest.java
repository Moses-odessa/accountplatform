package ua.moses.microservices.payments.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javacrumbs.jsonunit.core.Option;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.moses.microservices.payments.model.Payment;
import ua.moses.microservices.payments.model.PaymentsType;
import ua.moses.microservices.payments.service.PaymentsService;

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
@WebMvcTest(PaymentsController.class)
public class PaymentsControllerTest {
    @Value("${payments.endpoint.url}")
    private String paymentsEndpointUrl;

    @MockBean
    private PaymentsService paymentsService;

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
    public void getAllPaymentsTest() throws Exception {
        String ownerId = "owner";
        List<Payment> expectedList = Arrays.asList(new Payment("1", ownerId, "company1", "customer1", 1000, PaymentsType.IN, "serious comment" ),
                new Payment("2", ownerId, "company1", "customer2", 1000, PaymentsType.OUT, "serious comment2"));
        when(paymentsService.getAllPayments(ownerId)).thenReturn(expectedList);

        String result = mockMvc.perform(get(paymentsEndpointUrl + "/" + ownerId)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThatJson(result).when(Option.IGNORING_ARRAY_ORDER).isEqualTo(expectedList);
        verify(paymentsService).getAllPayments(ownerId);
        verifyNoMoreInteractions(paymentsService);

    }

    @Test
    public void getPaymentByIdTest() throws Exception {
        String paymentId = "5a981eaba3e33c120c2c67bf";
        String ownerId = "owner";
        Payment expected = new Payment(paymentId, ownerId, "company1", "customer1", 1000, PaymentsType.IN, "serious comment");
        when(paymentsService.getPaymentById(ownerId, paymentId)).thenReturn(expected);

        mockMvc.perform(get(paymentsEndpointUrl + "/owner/" + paymentId)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));
        verify(paymentsService).getPaymentById(ownerId, paymentId);
        verifyNoMoreInteractions(paymentsService);

    }

    @Test
    public void insertPaymentTest() throws Exception {
        Payment expected = new Payment("1","owner1", "company1", "customer1", 1000, PaymentsType.IN, "serious comment");
        when(paymentsService.insertPayment(any(Payment.class))).thenReturn(expected);

        mockMvc.perform(post(paymentsEndpointUrl)
                .content(convertToJsonString(expected))
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));

        verify(paymentsService).insertPayment(any(Payment.class));
        verifyNoMoreInteractions(paymentsService);
    }

    @Test
    public void deletePaymentTest() throws Exception {
        Payment expected = new Payment("1","owner1", "company1", "customer1", 1000, PaymentsType.IN, "serious comment");
        when(paymentsService.deletePayment(any(Payment.class))).thenReturn(expected);

        mockMvc.perform(delete(paymentsEndpointUrl)
                .contentType(APPLICATION_JSON_UTF8)
                .content(convertToJsonString(expected)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));

        verify(paymentsService).deletePayment(any(Payment.class));
        verifyNoMoreInteractions(paymentsService);
    }

    @Test
    public void updatePaymentTest() throws Exception {
        Payment expected = new Payment("1","owner1", "company1", "customer1", 1000, PaymentsType.IN, "serious comment");
        when(paymentsService.updatePayment(any(Payment.class))).thenReturn(expected);

        mockMvc.perform(put(paymentsEndpointUrl)
                .contentType(APPLICATION_JSON_UTF8)
                .content(convertToJsonString(expected)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));

        verify(paymentsService).updatePayment(any(Payment.class));
        verifyNoMoreInteractions(paymentsService);
    }

}
