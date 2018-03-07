package ua.moses.microservices.goods.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ua.moses.microservices.goods.model.Good;

import java.util.List;

public interface GoodsRepository extends MongoRepository<Good, String> {
    @Query("{'$and':[{'ownerId': ?0},{'deleted': false}]}")
    List<Good> getAllGoods(String ownerId);

    Good findByOwnerIdAndId(String ownerId, String goodId);

}
