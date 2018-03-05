package ua.moses.microservices.warehouses.service;

import ua.moses.microservices.warehouses.model.Warehouse;

import java.util.List;

public interface WarehousesService {

    List<Warehouse> getAllWarehouses(String ownerId);

    Warehouse insertWarehouse(Warehouse warehouse);

    Warehouse deleteWarehouse(Warehouse warehouse);

    Warehouse updateWarehouse(Warehouse warehouse);

    Warehouse getWarehouseById(String ownerId, String warehouseId);
}
