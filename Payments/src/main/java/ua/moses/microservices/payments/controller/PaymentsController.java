package ua.moses.microservices.payments.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.moses.microservices.payments.model.Payment;
import ua.moses.microservices.payments.service.PaymentsService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "${payments.endpoint.url}", produces = "application/json;charset=UTF-8")
public class PaymentsController {
    @Inject
    private PaymentsService paymentsService;

    @GetMapping(value = "{ownerId}")
    public ResponseEntity<List<Payment>> getAllPayments(@PathVariable String ownerId) {
        List<Payment> result = paymentsService.getAllPayments(ownerId);
        if (result == null) {
            result = new ArrayList<>(0);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "{ownerId}/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable String ownerId, @PathVariable String paymentId) {
        Payment result = paymentsService.getPaymentById(ownerId, paymentId);
        return ResponseEntity.ok(result);
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Payment> insertPayment(@Valid @RequestBody Payment payment) {
        Payment result = paymentsService.insertPayment(payment);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Payment> deletePayment(@Valid @RequestBody Payment payment) {
        Payment result = paymentsService.deletePayment(payment);
        return ResponseEntity.ok(result);
    }

    @PutMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Payment> updatePayment(@Valid @RequestBody Payment payment) {
        Payment result = paymentsService.updatePayment(payment);
        return ResponseEntity.ok(result);
    }

}
