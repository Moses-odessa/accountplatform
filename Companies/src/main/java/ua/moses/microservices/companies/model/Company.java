package ua.moses.microservices.companies.model;

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
public class Company {
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

    public Company(String ownerId, String name) {
        this.ownerId = ownerId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Warehouses{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}

