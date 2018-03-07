package ua.moses.microservices.goods.model;

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
public class Good {
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
    private String article = "";

    @Field
    private double price = 0;

    public Good(String ownerId, String name) {
        this.ownerId = ownerId;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Good{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", article='" + article + '\'' +
                ", price=" + price +
                '}';
    }
}

