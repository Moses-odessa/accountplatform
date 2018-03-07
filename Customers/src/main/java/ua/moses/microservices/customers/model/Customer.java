package ua.moses.microservices.customers.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Customer {
    @Id
    private String id;

    @NotEmpty
    @Indexed
    private String ownerId;

    @NotEmpty
    @Indexed
    private String name;

    @Field
    private boolean deleted = false;

    @Field
    private PhonesBook phonesBook = new PhonesBook();

    @Field
    private EmailsBook emailsBook = new EmailsBook();

    @Field
    private String city = "";

    @Field
    private String adressLine = "";

    @Field
    private String privateComment = "";

    public Customer(String ownerId, String name) {
        this.ownerId = ownerId;
        this.name = name;
    }

    public Customer(String customerId, String ownerId, String name, boolean deleted) {
        this.id = customerId;
        this.ownerId = ownerId;
        this.name = name;
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return deleted == customer.deleted &&
                Objects.equals(id, customer.id) &&
                Objects.equals(ownerId, customer.ownerId) &&
                Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, ownerId, name, deleted);
    }
}

