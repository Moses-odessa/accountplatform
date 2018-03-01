package ua.moses.microservices.warehouses.model;

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
public class Warehouse {
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

    public Warehouse(String ownerId, String name) {
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

