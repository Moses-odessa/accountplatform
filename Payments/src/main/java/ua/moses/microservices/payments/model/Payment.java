package ua.moses.microservices.payments.model;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document
public class Payment {
    @Id
    private String id;

    @NotEmpty
    @Indexed
    private String ownerId;

    @NotEmpty
    private String companyId;

    @NotEmpty
    private String customerId;

    @Field
    private double sum = 0;

    @Field
    private PaymentsType type = PaymentsType.IN;

    @Field
    private String comment;

    @Field
    private boolean deleted = false;


    public Payment(String id, String ownerId, String companyId, String customerId, double sum, PaymentsType type, String comment) {
        this.id = id;
        this.ownerId = ownerId;
        this.companyId = companyId;
        this.customerId = customerId;
        this.sum = sum;
        this.type = type;
        this.comment = comment;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", sum=" + sum +
                ", type=" + type +
                '}';
    }
}

