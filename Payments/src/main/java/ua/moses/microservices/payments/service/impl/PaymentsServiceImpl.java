package ua.moses.microservices.payments.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ua.moses.microservices.payments.model.Payment;
import ua.moses.microservices.payments.repository.PaymentsRepository;
import ua.moses.microservices.payments.service.PaymentsService;

import javax.inject.Inject;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsServiceImpl implements PaymentsService {
    @Inject
    private PaymentsRepository paymentsRepository;

    @Override
    public List<Payment> getAllPayments(String ownerId) {
        return paymentsRepository.getAllPayments(ownerId);
    }

    @Override
    public Payment insertPayment(Payment payment) {
        return paymentsRepository.insert(payment);
    }

    @Override
    public Payment deletePayment(Payment payment) {
        if (paymentsRepository.exists(payment.getId())) {
            payment.setDeleted(true);
            paymentsRepository.save(payment);
            return payment;
        } else {
            return null;
        }
    }

    @Override
    public Payment updatePayment(Payment payment) {
        if (paymentsRepository.exists(payment.getId())) {
            return paymentsRepository.save(payment);
        } else {
            return null;
        }
    }

    @Override
    public Payment getPaymentById(String ownerId, String paymentId) {
        return paymentsRepository.findByOwnerIdAndId(ownerId, paymentId);
    }
}
