package ua.moses.microservices.payments.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ua.moses.microservices.payments.model.Payment;

import java.util.List;

public interface PaymentsRepository extends MongoRepository<Payment, String> {
    @Query("{'$and':[{'ownerId': ?0},{'deleted': false}]}")
    List<Payment> getAllPayments(String ownerId);

    Payment findByOwnerIdAndId(String ownerId, String paymentId);

}
