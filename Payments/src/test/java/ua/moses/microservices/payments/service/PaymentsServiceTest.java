package ua.moses.microservices.payments.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.moses.microservices.payments.model.Payment;
import ua.moses.microservices.payments.model.PaymentsType;
import ua.moses.microservices.payments.repository.PaymentsRepository;
import ua.moses.microservices.payments.service.impl.PaymentsServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentsServiceTest {

    private PaymentsService paymentsService;

    @MockBean
    private PaymentsRepository paymentsRepository;

    @Before
    public void init() {
        paymentsService = new PaymentsServiceImpl(paymentsRepository);
    }

    @Test
    public void getAllPaymentsTest() {
        String ownerId = "owner";
        List<Payment> expectedList = Arrays.asList(new Payment("1", ownerId, "company1", "customer1", 1000, PaymentsType.IN, "serious comment"),
                new Payment("1", ownerId, "company1", "customer2", 1000, PaymentsType.OUT, "serious comment2"));
        when(paymentsRepository.getAllPayments(ownerId)).thenReturn(expectedList);
        List<Payment> result = paymentsService.getAllPayments(ownerId);
        assertEquals(expectedList, result);
    }

    @Test
    public void getPaymentByIdTest() {
        String paymentId = "5a981eaba3e33c120c2c67bf";
        String ownerId = "owner";
        Payment expected = new Payment(paymentId, ownerId, "company1", "customer1", 1000, PaymentsType.IN, "serious comment");
        when(paymentsRepository.findByOwnerIdAndId(ownerId, paymentId)).thenReturn(expected);
        Payment result = paymentsService.getPaymentById(ownerId, paymentId);
        assertEquals(expected, result);
    }

    @Test
    public void insertPaymentTest() {
        Payment expected = new Payment("1", "owner1", "company1", "customer1", 1000, PaymentsType.IN, "serious comment");
        when(paymentsRepository.insert(expected)).thenReturn(expected);
        Payment result = paymentsService.insertPayment(expected);
        assertEquals(expected, result);
    }


    @Test
    public void deletePaymentTest() {
        Payment given = new Payment("1", "owner1", "company1", "customer1", 1000, PaymentsType.IN, "serious comment");
        Payment expected = new Payment("1", "owner1", "company1", "customer1", 1000, PaymentsType.IN, "serious comment");
        expected.setDeleted(true);
        when(paymentsRepository.exists(given.getId())).thenReturn(true);
        Payment result = paymentsService.deletePayment(given);
        assertEquals(expected, result);
    }

    @Test
    public void updatePaymentTest() {
        Payment expected = new Payment("1", "owner1", "company1", "customer1", 1000, PaymentsType.IN, "serious comment");
        expected.setDeleted(true);
        when(paymentsRepository.exists(expected.getId())).thenReturn(true);
        when(paymentsRepository.save(expected)).thenReturn(expected);
        Payment result = paymentsService.updatePayment(expected);
        assertEquals(expected, result);
    }

}
