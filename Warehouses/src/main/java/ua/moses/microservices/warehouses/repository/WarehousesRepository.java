package ua.moses.microservices.warehouses.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.moses.microservices.warehouses.model.Warehouse;

import java.util.List;

public interface WarehousesRepository extends MongoRepository<Warehouse, String> {
    public Warehouse findByName(String name);
    public List<Warehouse> findAll();
    public List<Warehouse> findByOwnerId(String ownerId);

}
