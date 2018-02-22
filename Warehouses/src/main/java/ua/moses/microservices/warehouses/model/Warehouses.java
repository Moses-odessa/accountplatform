package ua.moses.microservices.warehouses.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Warehouses {
    @Id
    private String id;

    @NotEmpty
    @Indexed(unique=true)
    private String name;

    public Warehouses(String name) {
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
