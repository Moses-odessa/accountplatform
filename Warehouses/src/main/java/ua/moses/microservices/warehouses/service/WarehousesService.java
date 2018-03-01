package ua.moses.microservices.warehouses.service;

import ua.moses.microservices.warehouses.model.Warehouse;

import java.util.List;

public interface WarehousesService {

    List<Warehouse> getAllWarehouses();

    Warehouse insertWarehous(Warehouse warehous);

    Warehouse deleteWarehous(Warehouse warehous);

    Warehouse updateWarehous(Warehouse warehous);
}
