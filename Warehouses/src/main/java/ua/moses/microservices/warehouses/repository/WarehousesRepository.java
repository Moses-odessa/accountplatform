package ua.moses.microservices.warehouses.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.moses.microservices.warehouses.model.Warehouses;

import java.util.List;

public interface WarehousesRepository extends MongoRepository<Warehouses, String> {
    public Warehouses findByName(String name);
    public List<Warehouses> findAll();
}
