package ua.moses.microservices.payments.service;

import ua.moses.microservices.payments.model.Payment;

import java.util.List;

public interface PaymentsService {

    List<Payment> getAllPayments(String ownerId);

    Payment insertPayment(Payment payment);

    Payment deletePayment(Payment payment);

    Payment updatePayment(Payment payment);

    Payment getPaymentById(String ownerId, String paymentId);
}
