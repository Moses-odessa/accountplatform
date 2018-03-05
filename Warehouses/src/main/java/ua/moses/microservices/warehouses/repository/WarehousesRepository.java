package ua.moses.microservices.warehouses.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ua.moses.microservices.warehouses.model.Warehouse;

import java.util.List;

public interface WarehousesRepository extends MongoRepository<Warehouse, String> {
    @Query("{'$and':[{'ownerId': ?0},{'deleted': false}]}")
    List<Warehouse> getAllWarehouses(String ownerId);

    Warehouse findByOwnerIdAndId(String ownerId, String warehousesId);

}
